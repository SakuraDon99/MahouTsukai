package com.sakuradon.mahoutsukai.core;

import java.util.Queue;

/**
 * @author SakuraDon
 */
public interface Executor {

    /**
     * 执行任务
     *
     * @param taskQueue task queue
     */
    void execute(Queue<Class<? extends Task>> taskQueue);

}
