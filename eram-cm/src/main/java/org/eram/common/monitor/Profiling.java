package org.eram.common.monitor;

import java.util.HashMap;
import java.util.Map;

public abstract class Profiling implements Profiler {

    protected String appName;
    protected String taskName;
    protected Map<String, Double> data;


    public Profiling(String appName, String taskName) {
        this.appName = appName;
        this.taskName = taskName;

        this.data = new HashMap<>();
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Map<String, Double> getData() {
        return data;
    }

    public void setData(Map<String, Double> data) {
        this.data = data;
    }

}
