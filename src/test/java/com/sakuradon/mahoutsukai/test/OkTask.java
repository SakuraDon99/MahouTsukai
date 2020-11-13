package com.sakuradon.mahoutsukai.test;

import com.sakuradon.mahoutsukai.annotation.EnableTask;
import com.sakuradon.mahoutsukai.core.AbstractTask;
import com.sakuradon.mahoutsukai.core.TaskChain;

@EnableTask
public class OkTask extends AbstractTask {

    @Override
    public void execute(TaskChain taskChain) {
        System.out.println("ok");
    }

}
