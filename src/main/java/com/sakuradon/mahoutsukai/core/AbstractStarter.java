package com.sakuradon.mahoutsukai.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sakuradon.mahoutsukai.android.Adb;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.config.ConfigLoader;
import com.sakuradon.mahoutsukai.entity.GlobalEntity;
import com.sakuradon.mahoutsukai.exception.SystemException;
import com.sakuradon.mahoutsukai.exception.TimeoutException;
import com.sakuradon.mahoutsukai.inject.BasicModule;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import com.sakuradon.mahoutsukai.log.LoggerRole;
import jdk.internal.instrumentation.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author SakuraDon
 */
public abstract class AbstractStarter {

    private static final Logger LOGGER = LoggerFactory.createLogger(LoggerRole.SYSTEM);

    private final Queue<Class<? extends Task>> taskQueue = new LinkedList<>();

    private final List<Class<? extends GlobalEntity>> globalEntityList = new ArrayList<>();

    /**
     * starter name
     *
     * @return name
     */
    protected abstract String getName();

    public AbstractStarter nextTask(Class<? extends Task> clz) {
        taskQueue.add(clz);
        return this;
    }

    public AbstractStarter registerGlobalEntity(Class<? extends GlobalEntity> clz) {
        globalEntityList.add(clz);
        return this;
    }

    public void start(String[] args) {
        try {
            LOGGER.info("starter {" + getName() + "} starting...");
            BasicModule basicModule = new BasicModule();
            SessionFactory sessionFactory = new SessionFactoryImpl();
            Config config = loadConfig();
            Adb adb = new Adb(config.getAdbPath(), null);
            Session session = sessionFactory.createAndroidSession("", adb);
            basicModule.setConfig(config);
            basicModule.setTaskQueue(taskQueue);
            basicModule.setGlobalEntityList(globalEntityList);
            basicModule.setAdb(adb);
            basicModule.setSession(session);
            Injector injector = Guice.createInjector(basicModule);
            initializeGlobalEntity(injector);
            Executor executor = injector.getInstance(Executor.class);
            executor.execute(taskQueue);
        } catch (SystemException e) {
            LOGGER.error(e.getCode() + " " +e.getMsg());
        } catch (TimeoutException e) {
            LOGGER.error("timeout! " + e.getRealTime() + "/" + e.getLimitTime());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    private Config loadConfig() {
        ConfigLoader configLoader = new ConfigLoader();
        return configLoader.load("config.json");
    }

    private void initializeGlobalEntity(Injector injector) {
        for (Class<? extends GlobalEntity> clz : globalEntityList) {
            injector.getInstance(clz).initialize();
        }
    }

}
