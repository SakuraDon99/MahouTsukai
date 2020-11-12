package com.sakuradon.mahoutsukai.core;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author SakuraDon
 */
public abstract class AbstractWorkflow implements Workflow {

    private final Queue<Class<? extends Task>> taskClassQueue = new LinkedList<>();

    private final Queue<Task> taskQueue = new LinkedList<>();

    @Override
    public Queue<Class<? extends Task>> getTaskClassQueue() {
        return taskClassQueue;
    }

    @Override
    public void addTask(Task task) {
        taskQueue.add(task);
    }

    @Override
    public Queue<Task> getTaskQueue() {
        return taskQueue;
    }

    protected void nextTask(Class<? extends Task> clz) {
        taskClassQueue.add(clz);
    }

}
