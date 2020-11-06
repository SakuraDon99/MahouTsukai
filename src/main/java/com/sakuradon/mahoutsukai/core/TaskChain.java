package com.sakuradon.mahoutsukai.core;

/**
 * @author SakuraDon
 */
public class TaskChain {

    private Class<? extends Task> preTask;

    private Class<? extends Task> nextTask;

    public Class<? extends Task> getPreTask() {
        return preTask;
    }

    public void setPreTask(Class<? extends Task> preTask) {
        this.preTask = preTask;
    }

    public Class<? extends Task> getNextTask() {
        return nextTask;
    }

    public void setNextTask(Class<? extends Task> nextTask) {
        this.nextTask = nextTask;
    }
}
