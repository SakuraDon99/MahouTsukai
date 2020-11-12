package com.sakuradon.mahoutsukai.android;

import com.google.inject.Inject;
import com.sakuradon.mahoutsukai.config.Config;
import com.sakuradon.mahoutsukai.exception.Exceptions;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import com.sakuradon.mahoutsukai.utils.StringUtil;
import jdk.internal.instrumentation.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author SakuraDon
 */
public class Adb {

    private static final Logger LOGGER = LoggerFactory.createLogger(Adb.class);

    private final String device;

    @Inject
    private Config config;

    public Adb(String device) {
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
            return config.getAdbPath() + " ";
        }
        return config.getAdbPath() + " -s " + device + " ";
    }

    private void exec(String script) {
        LOGGER.trace(String.format("adb exec {%s}", script));
        try {
            Process process = Runtime.getRuntime().exec(script);
            BufferedReader readStdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader readStderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String stdoutTmp;
            String stderrTmp;
            StringBuilder stdoutSb = new StringBuilder();
            StringBuilder stderrSb = new StringBuilder();
            while ((stdoutTmp = readStdout.readLine()) != null) {
                stdoutSb.append(stdoutTmp).append("\n");
            }
            while ((stderrTmp = readStderr.readLine()) != null) {
                stderrSb.append(stderrTmp).append("\n");
            }
            String stdout = stdoutSb.toString();
            String stderr = stderrSb.toString();
            if (!StringUtil.isBlank(stderr)) {
                LOGGER.trace("exec error:" + "\n" + stderr);
                throw Exceptions.ADB_EXEC_FAILED;
            }
            if (!StringUtil.isBlank(stdout)) {
                LOGGER.trace("exec result:" + "\n" + stdout);
            }
        } catch (IOException e) {
            throw Exceptions.ADB_EXEC_FAILED;
        }
    }

}
