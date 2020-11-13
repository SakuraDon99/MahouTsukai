package com.sakuradon.mahoutsukai.log;

import com.sakuradon.mahoutsukai.core.Task;

/**
 * @author SakuraDon
 */
public class LoggerFactory {

    private static final String[] SYSTEM_PKG = {
            "com.sakuradon.mahoutsukai.android",
            "com.sakuradon.mahoutsukai.config",
            "com.sakuradon.mahoutsukai.core",
            "com.sakuradon.mahoutsukai.cv",
            "com.sakuradon.mahoutsukai.entity",
            "com.sakuradon.mahoutsukai.inject"
    };

    private static LoggerLevel loggerLevel = LoggerLevel.INFO;

    private static boolean fullClassName = false;

    public static void setLoggerLevel(String level) {
        loggerLevel = LoggerLevel.valueOf(level);
    }

    public static void setFullClassName(boolean fullClassName) {
        LoggerFactory.fullClassName = fullClassName;
    }

    public static Logger createLogger(Class<?> clz) {
        LoggerRole role = LoggerRole.UNKNOWN;
        if (Task.class.isAssignableFrom(clz)) {
            role = LoggerRole.TASK;
        } else {
            for (String s : SYSTEM_PKG) {
                if (clz.getName().startsWith(s)) {
                    role = LoggerRole.SYSTEM;
                    break;
                }
            }
        }
        return new LoggerImpl(role, fullClassName ? clz.getName() : clz.getSimpleName(), loggerLevel);
    }

}
