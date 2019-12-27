package org.eram.common.monitor;

import java.util.Map;

public interface Profiler {

    void startProfiler();
    Map<String, Double> collect();
    void stopProfiler();
}
