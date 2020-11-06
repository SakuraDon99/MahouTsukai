package com.sakuradon.mahoutsukai.log;

import com.sakuradon.mahoutsukai.core.Task;
import jdk.internal.instrumentation.Logger;

/**
 * @author SakuraDon
 */
public class LoggerFactory {

    private static LoggerLevel loggerLevel = LoggerLevel.INFO;

    public static void setLoggerLevel(LoggerLevel level) {
        loggerLevel = level;
    }

    public static Logger createLogger(Class<? extends Task> clz) {
        return new LoggerImpl(clz.getSimpleName(), loggerLevel);
    }

    public static Logger createLogger(LoggerRole role) {
        return new LoggerImpl(role.getName(), loggerLevel);
    }

}
