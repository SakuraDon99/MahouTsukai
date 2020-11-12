package com.sakuradon.mahoutsukai.core;

import java.util.Queue;

/**
 * @author SakuraDon
 */
public interface Workflow {

    /**
     * workflow name
     *
     * @return name
     */
    String getName();

    /**
     * 获取任务配置队列
     *
     * @return 任务配置队列
     */
    Queue<Class<? extends Task>> getTaskClassQueue();

    /**
     * 添加任务
     *
     * @param task 任务
     */
    void addTask(Task task);

    /**
     * 获取任务队列
     *
     * @return 任务队列
     */
    Queue<Task> getTaskQueue();

    /**
     * configure
     */
    void configure();

}
