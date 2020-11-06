package com.sakuradon.mahoutsukai;

import com.sakuradon.mahoutsukai.core.AbstractStarter;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import com.sakuradon.mahoutsukai.log.LoggerLevel;

public class TestStarter extends AbstractStarter {

    public static void main(String[] args) {
        TestStarter starter = new TestStarter();
        starter.registerGlobalEntity(TestGlobalEntity.class).nextTask(TestTask.class).start(args);
    }

    @Override
    protected String getName() {
        return "test starter";
    }

}
