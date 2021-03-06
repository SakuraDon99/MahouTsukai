package com.sakuradon.mahoutsukai.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sakuradon.mahoutsukai.android.Adb;
import com.sakuradon.mahoutsukai.annotation.EnableTask;
import com.sakuradon.mahoutsukai.annotation.EnableWorkflow;
import com.sakuradon.mahoutsukai.annotation.EntityRepository;
import com.sakuradon.mahoutsukai.config.BootParameter;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.config.ConfigLoader;
import com.sakuradon.mahoutsukai.entity.GlobalEntity;
import com.sakuradon.mahoutsukai.exception.Exceptions;
import com.sakuradon.mahoutsukai.exception.SystemException;
import com.sakuradon.mahoutsukai.inject.*;
import com.sakuradon.mahoutsukai.log.Logger;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import com.sakuradon.mahoutsukai.utils.ClassUtil;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.*;

/**
 * @author SakuraDon
 */
public class Starter {

    private static Logger LOGGER = LoggerFactory.createLogger(Starter.class);

    private final Class<?> main;

    private final Map<String, Class<?>> workflowMap = new HashMap<>();

    private final List<Class<?>> globalEntityList = new ArrayList<>();

    public Starter(Class<?> main) {
        this.main = main;
    }

    public void run(String[] args) {
        try {
            BootParameter bootParameter = new BootParameter();
            CmdLineParser cmdLineParser = new CmdLineParser(bootParameter);
            cmdLineParser.parseArgument(args);
            LoggerFactory.setLoggerLevel(bootParameter.getLogLevel());
            LoggerFactory.setFullClassName(bootParameter.isFullClassName());
            String configPath = bootParameter.getConfig();
            String[] devices = bootParameter.getDevice().split(",");
            String[] workflows = bootParameter.getWorkflow().split(",");
            LOGGER = LoggerFactory.createLogger(Starter.class);

            BasicModule basicModule = new BasicModule();
            ConfigLoader configLoader = new ConfigLoader();
            Config config = configLoader.load(configPath);
            basicModule.setConfig(config);
            GlobalEntityModule globalEntityModule = new GlobalEntityModule();
            WorkflowModule workflowModule = new WorkflowModule();
            TaskModule taskModule = new TaskModule();
            String mainClzName = main.getName();
            String pkg = mainClzName.substring(0, mainClzName.length() - main.getSimpleName().length() - 1);
            Set<Class<?>> set = ClassUtil.getClasses(pkg);
            for (Class<?> clz : set) {
                if (Workflow.class.isAssignableFrom(clz) && clz.getAnnotation(EnableWorkflow.class) != null) {
                    EnableWorkflow enableWorkflow = clz.getAnnotation(EnableWorkflow.class);
                    String name = enableWorkflow.value();
                    if ("".equals(name)) {
                        name = clz.getSimpleName();
                    }
                    workflowMap.put(name, clz);
                    workflowModule.addClass(clz);
                } else if (Task.class.isAssignableFrom(clz) && clz.getAnnotation(EnableTask.class) != null) {
                    taskModule.addClass(clz);
                } else if (GlobalEntity.class.isAssignableFrom(clz) &&
                        clz.getAnnotation(EntityRepository.class) != null) {
                    globalEntityList.add(clz);
                    globalEntityModule.addClass(clz);
                }
            }
            // 依赖注入
            // baseInjector 通用依赖
            Injector baseInjector = Guice.createInjector(basicModule, globalEntityModule, workflowModule);
            for (Class<?> clz : globalEntityList) {
                ((GlobalEntity) baseInjector.getInstance(clz)).initialize();
            }

            for (String dev : devices) {
                Adb adb = new Adb(dev);
                AndroidModule androidModule = new AndroidModule();
                androidModule.setAdb(adb);
                // platformInjector 平台依赖
                Injector platformInjector = baseInjector.createChildInjector(androidModule);
                // workInjector 工作依赖
                Injector workInjector = platformInjector.createChildInjector(taskModule);
                Executor executor = workInjector.getInstance(Executor.class);
                List<Workflow> workflowList = new ArrayList<>();
                for (String wf : workflows) {
                    if (workflows.length == 1 && "".equals(wf)) {
                        if (workflowMap.size() > 1) {
                            throw Exceptions.NO_SELECT_WORKFLOW;
                        }
                        if (workflowMap.size() == 0) {
                            throw Exceptions.NO_ENABLE_WORKFLOW;
                        }
                        workflowMap.forEach((k, workflow) -> workflowList.add(instanceWorkflowTask(k, workInjector)));
                    } else {
                        workflowList.add(instanceWorkflowTask(wf, workInjector));
                    }
                }
                executor.execute(adb.getDevice(), workflowList);
            }
        } catch (CmdLineException e) {
            LOGGER.error(e, "resolve parameter failed");
        } catch (SystemException e) {
            LOGGER.error(e, "(code %d) %s", e.getCode(), e.getMsg());
        } catch (Exception e) {
            LOGGER.error(e, "system start failed");
        }
    }

    private Workflow instanceWorkflowTask(String name, Injector injector) {
        Class<?> clz = workflowMap.get(name);
        if (clz == null) {
            throw Exceptions.WORKFLOW_NOT_FOUND;
        }
        Workflow workflow = (Workflow) injector.getInstance(clz);
        workflow.configure();
        for (Class<? extends Task> taskClz : workflow.getTaskClassQueue()) {
            Task task = injector.getInstance(taskClz);
            if (task == null) {
                throw Exceptions.TASK_NOT_FOUND;
            }
            workflow.addTask(task);
        }
        return workflow;
    }
}
