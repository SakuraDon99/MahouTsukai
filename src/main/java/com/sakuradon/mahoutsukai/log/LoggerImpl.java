package com.sakuradon.mahoutsukai.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author SakuraDon
 */
public class LoggerImpl implements jdk.internal.instrumentation.Logger {

    private final String role;

    private final LoggerLevel loggerLevel;

    private SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public LoggerImpl(String role, LoggerLevel loggerLevel) {
        this.role = role;
        this.loggerLevel = loggerLevel;
    }

    @Override
    public void error(String s) {
        if (loggerLevel.compareTo(LoggerLevel.ERROR) > 0) {
            return;
        }
        print(s, LoggerLevel.ERROR);
    }

    @Override
    public void warn(String s) {
        if (loggerLevel.compareTo(LoggerLevel.WARN) > 0) {
            return;
        }
        print(s, LoggerLevel.WARN);
    }

    @Override
    public void info(String s) {
        if (loggerLevel.compareTo(LoggerLevel.INFO) > 0) {
            return;
        }
        print(s, LoggerLevel.INFO);
    }

    @Override
    public void debug(String s) {
        if (loggerLevel.compareTo(LoggerLevel.DEBUG) > 0) {
            return;
        }
        print(s, LoggerLevel.DEBUG);
    }

    @Override
    public void trace(String s) {
        if (loggerLevel.compareTo(LoggerLevel.TRACE) > 0) {
            return;
        }
        print(s, LoggerLevel.TRACE);
    }

    @Override
    public void error(String s, Throwable throwable) {
        throw new UnsupportedOperationException();
    }

    private void print(String s, LoggerLevel l) {
        String date = f.format(new Date());
        String host = "[" + role + "]";
        String level = "[" + l.name() + "]";
        System.out.println(level + " " + date + " " + host + " - " + s);
    }

}
