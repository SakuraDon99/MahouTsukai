package com.sakuradon.mahoutsukai.test;

import com.sakuradon.mahoutsukai.annotation.EnableTask;
import com.sakuradon.mahoutsukai.core.Task;
import com.sakuradon.mahoutsukai.core.TaskChain;

@EnableTask
public class OkTask implements Task {
    @Override
    public String getName() {
        return "ok";
    }

    @Override
    public void execute(TaskChain taskChain) {
        System.out.println("ok");
    }
}
