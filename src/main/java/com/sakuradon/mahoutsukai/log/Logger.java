package com.sakuradon.mahoutsukai.log;

/**
 * @author SakuraDon
 */
public interface Logger {

    /**
     * error
     *
     * @param s message
     */
    void error(String s);

    /**
     * error
     *
     * @param s message
     */
    void warn(String s);

    /**
     * error
     *
     * @param s    message
     * @param args args
     */
    default void warn(String s, Object... args) {
        warn(String.format(s, args));
    }

    /**
     * error
     *
     * @param s message
     */
    void info(String s);

    /**
     * error
     *
     * @param s    message
     * @param args args
     */
    default void info(String s, Object... args) {
        info(String.format(s, args));
    }

    /**
     * error
     *
     * @param s message
     */
    void debug(String s);

    /**
     * error
     *
     * @param s    message
     * @param args args
     */
    default void debug(String s, Object... args) {
        debug(String.format(s, args));
    }

    /**
     * error
     *
     * @param s message
     */
    void trace(String s);

    /**
     * error
     *
     * @param s    message
     * @param args args
     */
    default void trace(String s, Object... args) {
        trace(String.format(s, args));
    }

    /**
     * error
     *
     * @param e throwable
     * @param s message
     */
    void error(Throwable e, String s);

    /**
     * error
     *
     * @param s    message
     * @param e    throwable
     * @param args args
     */
    default void error(Throwable e, String s, Object... args) {
        error(e, String.format(s, args));
    }

}
