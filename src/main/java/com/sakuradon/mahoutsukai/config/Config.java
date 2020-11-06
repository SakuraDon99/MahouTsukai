package com.sakuradon.mahoutsukai.config;

/**
 * @author SakuraDon
 */
public class Config {

    private String adbPath;

    private String elementPath;

    private String screenPath;

    private Boolean loop;

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

    public Boolean getLoop() {
        return loop;
    }

    public void setLoop(Boolean loop) {
        this.loop = loop;
    }

}
