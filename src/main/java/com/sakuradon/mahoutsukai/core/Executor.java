package com.sakuradon.mahoutsukai.core;

import java.util.List;

/**
 * @author SakuraDon
 */
public interface Executor {

    /**
     * 执行任务
     *
     * @param name         name
     * @param workflowList workflow列表
     */
    void execute(String name, List<Workflow> workflowList);


}
