package com.sakuradon.mahoutsukai.test;

import com.google.inject.Inject;
import com.sakuradon.mahoutsukai.android.Adb;
import com.sakuradon.mahoutsukai.annotation.EnableTask;
import com.sakuradon.mahoutsukai.core.AbstractTask;
import com.sakuradon.mahoutsukai.core.Task;
import com.sakuradon.mahoutsukai.core.TaskChain;
import com.sakuradon.mahoutsukai.core.Session;
import com.sakuradon.mahoutsukai.entity.EntityFactory;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import jdk.internal.instrumentation.Logger;

@EnableTask
public class TestTask extends AbstractTask {

    private static final Logger LOGGER = LoggerFactory.createLogger(TestTask.class);

    @Inject
    private EntityFactory entityFactory;

    @Inject
    private Session session;

    @Inject
    private Adb adb;

    @Inject
    private TestGlobalEntity testGlobalEntity;

    @Override
    public void execute(TaskChain taskChain) {
        LOGGER.info(testGlobalEntity.getDemoPoint().toString());
    }

}
