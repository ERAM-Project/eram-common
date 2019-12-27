package org.eram.common.monitor.net;

import org.eram.common.monitor.Profiling;

import java.util.Map;

public class NetworkProfiler extends Profiling {

    public NetworkProfiler(String appName, String taskName) {
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
