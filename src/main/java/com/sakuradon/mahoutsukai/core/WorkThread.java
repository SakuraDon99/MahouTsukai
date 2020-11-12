package com.sakuradon.mahoutsukai.core;

import com.sakuradon.mahoutsukai.exception.SystemException;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import jdk.internal.instrumentation.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author SakuraDon
 */
public class WorkThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.createLogger(WorkThread.class);
    
    private final String name;

    private final boolean loop;

    private final List<Workflow> workflowList;

    public WorkThread(String name, boolean loop, List<Workflow> workflowList) {
        this.name = name;
        this.loop = loop;
        this.workflowList = workflowList;
    }

    @Override
    public void run() {
        for (Workflow workflow : workflowList) {
            Thread.currentThread().setName(name + ":" + workflow.getName());
            LOGGER.info(String.format("workflow {%s} start", workflow.getName()));
            Queue<Task> taskQueue = workflow.getTaskQueue();
            Task inExecutionTask = null;
            try {
                Map<String, Integer> executionTimesMap = new HashMap<>(taskQueue.size());
                TaskChain taskChain = new TaskChain();
                while (!taskQueue.isEmpty()) {
                    Task task = taskQueue.poll();
                    Class<? extends Task> taskClz = task.getClass();
                    Integer executionTimes = executionTimesMap.getOrDefault(taskClz.getName(), -1) + 1;
                    executionTimesMap.put(taskClz.getName(), executionTimes);
                    if (loop) {
                        taskQueue.add(task);
                    }
                    inExecutionTask = task;
                    LOGGER.info(String.format("task {%s} start", task.getName()));
                    taskChain.setNextTask(taskQueue.isEmpty() ? null : taskQueue.peek().getClass());
                    taskChain.setExecutionTimes(executionTimes);
                    task.beforeExecute(taskChain);
                    task.execute(taskChain);
                    task.afterExecute(taskChain);
                    LOGGER.info(String.format("task {%s} completed", task.getName()));
                    taskChain.setPreTask(taskClz);
                }
                LOGGER.info(String.format("workflow {%s} completed", workflow.getName()));
            } catch (SystemException e) {
                LOGGER.error(String.format("(code %d) %s", e.getCode(), e.getMsg()), e);
            } catch (Exception e) {
                LOGGER.error(String.format("task {%s} execute failed",
                        inExecutionTask == null ? "null" : inExecutionTask.getName()), e);
            }
        }
    }

}
