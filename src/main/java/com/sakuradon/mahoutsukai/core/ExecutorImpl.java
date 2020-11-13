package com.sakuradon.mahoutsukai.core;

import com.google.inject.Inject;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.log.Logger;
import com.sakuradon.mahoutsukai.log.LoggerFactory;

import java.util.List;

/**
 * @author SakuraDon
 */
public class ExecutorImpl implements Executor {

    private static final Logger LOGGER = LoggerFactory.createLogger(ExecutorImpl.class);

    @Override
    public void execute(String name, List<Workflow> workflowList) {
        LOGGER.debug("start new thread {%s}", name);
        new WorkThread(name, workflowList).start();
    }

}
