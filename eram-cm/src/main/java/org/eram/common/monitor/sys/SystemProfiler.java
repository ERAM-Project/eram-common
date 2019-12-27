package org.eram.common.monitor.sys;

import org.eram.common.monitor.Profiling;

import java.util.ArrayList;
import java.util.Map;

public class SystemProfiler extends Profiling {


    private ArrayList<Double> currentFrequencies;
    private double cpuUsage; // In percentage.
    private double batteryVoltage;
    private double batteryLevel;

    private static final String dataNames[] = {"CPU-frequency","CPU-Usage","Battery Voltage",
            "Battery Level"};


    public SystemProfiler(String appName, String taskName) {
        super(appName, taskName);
        currentFrequencies = new ArrayList<Double>();
        cpuUsage = 0.0;
        this.batteryVoltage = 0.0;
        this.batteryLevel = 0.0;

    }



    @Override
    public void startProfiler() {

        this.batteryVoltage = BatteryInfos.getCurrentVoltage();
    }

    @Override
    public Map<String, Double> collect() {

        int indexData = 0;

        for(int i=0; i< currentFrequencies.size(); i++)
        {
            this.data.put(dataNames[indexData]+""+(i+1), currentFrequencies.get(i));
        }

        indexData++;
        this.data.put(dataNames[indexData], cpuUsage);

        indexData++;
        this.data.put(dataNames[indexData], batteryVoltage);

        indexData++;
        this.data.put(dataNames[indexData], batteryLevel);

        return this.data;
    }

    @Override
    public void stopProfiler() {

    }
}
