package com.sakuradon.mahoutsukai.config;

import com.sakuradon.mahoutsukai.annotation.DefaultValue;

/**
 * @author SakuraDon
 */
public class Config {

    @DefaultValue("./adb")
    private String adbPath;

    @DefaultValue("./element")
    private String elementPath;

    @DefaultValue("./screen")
    private String screenPath;

    public String getAdbPath() {
        return adbPath;
    }

    public void setAdbPath(String adbPath) {
        this.adbPath = adbPath;
    }

    public String getElementPath() {
        return elementPath;
    }

    public void setElementPath(String elementPath) {
        this.elementPath = elementPath;
    }

    public String getScreenPath() {
        return screenPath;
    }

    public void setScreenPath(String screenPath) {
        this.screenPath = screenPath;
    }

    @Override
    public String toString() {
        return "Config{" +
                "adbPath='" + adbPath + '\'' +
                ", elementPath='" + elementPath + '\'' +
                ", screenPath='" + screenPath + '\'' +
                '}';
    }

}
