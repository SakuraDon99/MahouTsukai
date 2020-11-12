package com.sakuradon.mahoutsukai.exception;

/**
 * @author SakuraDon
 */
public class TimeoutException extends RuntimeException {

    private final long limitTime;

    private final long realTime;

    public TimeoutException(long limitTime, long realTime) {
        this.limitTime = limitTime;
        this.realTime = realTime;
    }

    public long getLimitTime() {
        return limitTime;
    }

    public long getRealTime() {
        return realTime;
    }
}
