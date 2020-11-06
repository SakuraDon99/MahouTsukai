package com.sakuradon.mahoutsukai.config;

import com.google.gson.Gson;
import com.sakuradon.mahoutsukai.log.LoggerFactory;
import com.sakuradon.mahoutsukai.log.LoggerRole;
import jdk.internal.instrumentation.Logger;

import java.io.*;
import java.lang.reflect.Field;

/**
 * @author SakuraDon
 */
public class ConfigLoader {

    private static final Logger LOGGER = LoggerFactory.createLogger(LoggerRole.SYSTEM);

    public Config load(String path) {
        Config config;
        try {
            LOGGER.info("loading config from {" + path + "}");
            StringBuilder result = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String s;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator()).append(s);
            }
            br.close();
            String conf = result.toString();
            Gson gson = new Gson();
            config = gson.fromJson(conf, Config.class);
        } catch (Exception e) {
            LOGGER.warn("load config failed, use the default config");
            config = new Config();
        }
        if (config.getAdbPath() == null) {
            LOGGER.warn("config 'adbPath' is null, use the default value 'adb'");
            config.setAdbPath("adb");
        }
        if (config.getElementPath() == null) {
            LOGGER.warn("config 'elementPath' is null, use the default value './element'");
            config.setElementPath("./element");
        }
        if (config.getScreenPath() == null) {
            LOGGER.warn("config 'screenPath' is null, use the default value './screen'");
            config.setScreenPath("./screen");
        }
        if (config.getLoop() == null) {
            LOGGER.warn("config 'loop' is null, use the default value 'false'");
            config.setLoop(false);
        }
        return config;
    }

}
