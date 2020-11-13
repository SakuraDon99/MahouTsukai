package com.sakuradon.mahoutsukai.test;

import com.sakuradon.mahoutsukai.annotation.EnableWorkflow;
import com.sakuradon.mahoutsukai.core.AbstractWorkflow;

/**
 * @author SakuraDon
 */
@EnableWorkflow("test")
public class TestWorkflow extends AbstractWorkflow {

    @Override
    public void configure() {
        nextTask(TestTask.class);
    }

}
