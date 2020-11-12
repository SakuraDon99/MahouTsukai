package com.sakuradon.mahoutsukai.log;

import jdk.internal.instrumentation.Logger;
import org.fusesource.jansi.Ansi;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author SakuraDon
 */
public class LoggerImpl implements Logger {

    private final LoggerRole role;

    private final String clz;

    private final LoggerLevel loggerLevel;

    public LoggerImpl(LoggerRole role, String clz, LoggerLevel loggerLevel) {
        this.role = role;
        this.clz = clz;
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
        error(s);
        throwable.printStackTrace();
    }

    private void print(String s, LoggerLevel l) {
        String threadName = Thread.currentThread().getName();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = f.format(new Date());
        System.out.println(Ansi.ansi()
                .fg(getLogLevelColor(l)).a(String.format("[%s] ", l.name()))
                .fg(Ansi.Color.WHITE).a(String.format("%s ", date))
                .fgCyan().a(String.format("[%s:%s] ", threadName, role.getName()))
                .fgMagenta().a(String.format("- %s - ", clz)).reset()
                .a(String.format("%s", s)).reset());
    }

    private Ansi.Color getLogLevelColor(LoggerLevel level) {
        switch (level) {
            case TRACE:
                return Ansi.Color.BLUE;
            case DEBUG:
                return Ansi.Color.MAGENTA;
            case INFO:
                return Ansi.Color.GREEN;
            case WARN:
                return Ansi.Color.YELLOW;
            case ERROR:
                return Ansi.Color.RED;
            default:
                return Ansi.Color.DEFAULT;
        }
    }
}
