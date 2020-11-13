package com.sakuradon.mahoutsukai.core;

import com.sakuradon.mahoutsukai.annotation.EnableWorkflow;
import com.sakuradon.mahoutsukai.exception.Exceptions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author SakuraDon
 */
public abstract class AbstractWorkflow implements Workflow {

    private final Queue<Class<? extends Task>> taskClassQueue = new LinkedList<>();

    private final Queue<Task> taskQueue = new LinkedList<>();

    private String name = null;

    @Override
    public final String getName() {
        if (name != null) {
            return name;
        }
        Class<?> clz = this.getClass();
        EnableWorkflow enableWorkflow = clz.getAnnotation(EnableWorkflow.class);
        if (enableWorkflow == null) {
            throw Exceptions.ILLEGAL_WORKFLOW;
        }
        name = "".equals(enableWorkflow.value()) ? clz.getSimpleName() : enableWorkflow.value();
        return name;
    }

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
