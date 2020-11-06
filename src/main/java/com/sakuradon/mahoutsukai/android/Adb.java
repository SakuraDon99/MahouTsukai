package com.sakuradon.mahoutsukai.android;

import com.sakuradon.mahoutsukai.log.LoggerFactory;
import com.sakuradon.mahoutsukai.log.LoggerRole;
import com.sakuradon.mahoutsukai.utils.StringUtil;
import jdk.internal.instrumentation.Logger;

/**
 * @author SakuraDon
 */
public class Adb {

    private static final Logger LOGGER = LoggerFactory.createLogger(LoggerRole.SYSTEM);

    private final String adbPath;

    private final String device;

    public Adb(String adbPath, String device) {
        this.adbPath = adbPath;
        this.device = device;
    }

    public String getDevice() {
        return device;
    }

    public void screenCap(String path) {
        exec(host() + "shell screencap " + path);
    }

    public void pull(String src, String tar) {
        exec(host()+ "pull " + src + " " + tar);
    }

    public void tap(int x, int y) {
        exec(host() + "shell input tap " + x + " " + y);
    }

    public void swipe(int x1, int y1, int x2, int y2, int ms) {
        exec(host() + "shell input swipe " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + ms);
    }

    public void text(String text) {
        exec(host() + "shell input text " + text);
    }

    public void key(int k) {
        exec(host() + "shell input keyevent " + k);
    }

    private String host() {
        if (StringUtil.isBlank(device)) {
            return adbPath + " ";
        }
        return adbPath + " -s " + device + " ";
    }

    private void exec(String script) {
        LOGGER.trace(script);
    }

}
