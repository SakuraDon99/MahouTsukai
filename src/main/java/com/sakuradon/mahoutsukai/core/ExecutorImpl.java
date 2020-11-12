package com.sakuradon.mahoutsukai.core;

import com.google.inject.Inject;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import jdk.internal.instrumentation.Logger;

import java.util.List;

/**
 * @author SakuraDon
 */
public class ExecutorImpl implements Executor {

    private static final Logger LOGGER = LoggerFactory.createLogger(ExecutorImpl.class);

    @Inject
    private Config config;

    @Override
    public void execute(String name, List<Workflow> workflowList) {
        LOGGER.debug(String.format("start new thread {%s}", name));
        new WorkThread(name, config.getLoop(), workflowList).start();
    }

}
