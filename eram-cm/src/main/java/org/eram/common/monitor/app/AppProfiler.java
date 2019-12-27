package org.eram.common.monitor.app;

import org.eram.common.monitor.Profiling;

import java.util.Map;

public class AppProfiler extends Profiling {

    public AppProfiler(String appName, String taskName)
    {
        super(appName, taskName);
    }

    @Override
    public void startProfiler() {

    }

    @Override
    public Map<String, Double> collect() {
        return null;
    }

    @Override
    public void stopProfiler() {

    }

}
