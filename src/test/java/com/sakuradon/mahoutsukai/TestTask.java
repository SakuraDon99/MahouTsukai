package com.sakuradon.mahoutsukai;

import com.google.inject.Inject;
import com.sakuradon.mahoutsukai.core.AbstractTask;
import com.sakuradon.mahoutsukai.core.Session;
import com.sakuradon.mahoutsukai.core.TaskChain;
import com.sakuradon.mahoutsukai.entity.Element;
import com.sakuradon.mahoutsukai.entity.EntityFactory;
import com.sakuradon.mahoutsukai.entity.Point;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import jdk.internal.instrumentation.Logger;

public class TestTask extends AbstractTask {

    private static final Logger LOGGER = LoggerFactory.createLogger(TestTask.class);

    @Inject
    private EntityFactory entityFactory;

    @Inject
    private Session session;

    @Inject
    private TestGlobalEntity testGlobalEntity;

    @Override
    public String getName() {
        return "test task";
    }

    @Override
    public void execute(TaskChain taskChain) {
        LOGGER.info(testGlobalEntity.getDemoPoint().toString());
        session.click(1, 4);
    }

}
