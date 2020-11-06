package com.sakuradon.mahoutsukai.core;

/**
 * @author SakuraDon
 */
public interface Task {

    /**
     * task name
     *
     * @return name
     */
    String getName();

    /**
     * beforeExecute
     *
     * @param taskChain chain params
     */
    default void beforeExecute(TaskChain taskChain) {}

    /**
     * execute
     *
     * @param taskChain chain params
     */
    void execute(TaskChain taskChain);

    /**
     * afterExecute
     *
     * @param taskChain chain params
     */
    default void afterExecute(TaskChain taskChain) {}

}
