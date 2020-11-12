package com.sakuradon.mahoutsukai.config;

import org.kohsuke.args4j.Option;

/**
 * @author SakuraDon
 */
public class BootParameter {

    @Option(name = "--logLevel")
    private String logLevel = "INFO";

    @Option(name = "--fullClassName")
    private boolean fullClassName = false;

    @Option(name = "--config")
    private String config = "config.json";

    @Option(name = "--device")
    private String device = "";

    @Option(name = "--workflow")
    private String workflow = "";

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public boolean isFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(boolean fullClassName) {
        this.fullClassName = fullClassName;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

}
