package com.sakuradon.mahoutsukai.log;

/**
 * @author SakuraDon
 */
public enum LoggerRole {

    /**
     * system
     */
    SYSTEM("system"),

    /**
     * task
     */
    TASK("task"),

    /**
     * known
     */
    UNKNOWN("unknown");

    /**
     * name
     */
    private final String name;

    LoggerRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
