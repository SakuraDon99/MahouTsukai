package com.sakuradon.mahoutsukai.config;

import com.google.gson.Gson;
import com.sakuradon.mahoutsukai.annotation.DefaultValue;
import com.sakuradon.mahoutsukai.exception.Exceptions;
import com.sakuradon.mahoutsukai.log.Logger;
import com.sakuradon.mahoutsukai.log.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author SakuraDon
 */
public class ConfigLoader {

    private static final Logger LOGGER = LoggerFactory.createLogger(ConfigLoader.class);

    public Config load(String path) {
        Config config;
        try {
            LOGGER.info("loading config from {%s}", path);
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
        try {
            Class<?> clz = config.getClass();
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clz);
                Method getMethod = pd.getReadMethod();
                Object invoke = getMethod.invoke(config);
                if (invoke == null) {
                    Method setMethod = pd.getWriteMethod();
                    String def = field.getAnnotation(DefaultValue.class).value();
                    String type = field.getGenericType().getTypeName();
                    LOGGER.warn("config '%s' is null, use the default value '%s'", field.getName(), def);
                    if ("java.lang.Boolean".equals(type)) {
                        setMethod.invoke(config, Boolean.getBoolean(def));
                    } else if ("java.lang.Integer".equals(type)) {
                        setMethod.invoke(config, Integer.getInteger(def));
                    } else if ("java.lang.Double".equals(type)) {
                        setMethod.invoke(config, Double.parseDouble(type));
                    } else {
                        setMethod.invoke(config, def);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw Exceptions.LOAD_CONFIG_FAILED;
        }
        return config;
    }

}
