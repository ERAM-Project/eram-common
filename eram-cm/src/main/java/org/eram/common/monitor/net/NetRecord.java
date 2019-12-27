package org.eram.common.monitor.net;

public class NetRecord {

    private double bandwidth;
    private double times;

    public NetRecord() {
        this(-1, -1);
    }

    /**
     * @param times
     */
    public NetRecord(double bandwidth, double times) {
       this.bandwidth = bandwidth;
       this.times = times;
    }

    public double getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(double bandwidth) {
        this.bandwidth = bandwidth;
    }

    public double getTimes() {
        return times;
    }

    public void setTimes(double times) {
        this.times = times;
    }
}
