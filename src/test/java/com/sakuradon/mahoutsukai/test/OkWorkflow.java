package com.sakuradon.mahoutsukai.test;

import com.sakuradon.mahoutsukai.annotation.EnableWorkflow;
import com.sakuradon.mahoutsukai.core.AbstractWorkflow;

@EnableWorkflow
public class OkWorkflow extends AbstractWorkflow {

    @Override
    public String getName() {
        return "ok";
    }

    @Override
    public void configure() {
        nextTask(OkTask.class);
    }

}
