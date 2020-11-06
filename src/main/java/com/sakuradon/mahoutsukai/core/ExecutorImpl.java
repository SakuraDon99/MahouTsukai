package com.sakuradon.mahoutsukai.core;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import com.sakuradon.mahoutsukai.log.LoggerRole;
import jdk.internal.instrumentation.Logger;

import java.util.Queue;

/**
 * @author SakuraDon
 */
public class ExecutorImpl implements Executor {

    private static final Logger LOGGER = LoggerFactory.createLogger(LoggerRole.SYSTEM);

    @Inject
    private Injector injector;

    @Inject
    private Config config;

    @Override
    public void execute(Queue<Class<? extends Task>> taskQueue) {
        TaskChain taskChain = new TaskChain();
        while (!taskQueue.isEmpty()) {
            Class<? extends Task> taskClz = taskQueue.poll();
            if (config.getLoop()) {
                taskQueue.add(taskClz);
            }
            Task task = injector.getInstance(taskClz);
            LOGGER.info("task {" + task.getName() + "} start");
            taskChain.setNextTask(taskQueue.peek());
            task.beforeExecute(taskChain);
            task.execute(taskChain);
            task.afterExecute(taskChain);
            LOGGER.info("task {" + task.getName() + "} completed");
            taskChain.setPreTask(taskClz);
        }
        LOGGER.info("all task completed");
    }

}
