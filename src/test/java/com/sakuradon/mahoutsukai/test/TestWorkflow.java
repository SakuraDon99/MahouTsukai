package com.sakuradon.mahoutsukai.test;

import com.google.inject.Inject;
import com.sakuradon.mahoutsukai.annotation.EnableWorkflow;
import com.sakuradon.mahoutsukai.core.AbstractWorkflow;

/**
 * @author SakuraDon
 */
@EnableWorkflow
public class TestWorkflow extends AbstractWorkflow {

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public void configure() {
        nextTask(TestTask.class);
    }

}
