package org.eram.common;

import org.eram.common.settings.ConnectionSettings;

public class Clone {

    private static final long serialVersionUID = 112233445566778899L;

    private String ip;
    private int profilerPort;
    private int offloadingPort;

    public  Clone(String ip){

        this.ip = ip;
        this.profilerPort   = ConnectionSettings.REMOTE_PROFILER_PORT;
        this.offloadingPort = ConnectionSettings.REMOTE_ERAM_PORT;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getProfilerPort() {
        return profilerPort;
    }

    public void setProfilerPort(int profilerPort) {
        this.profilerPort = profilerPort;
    }

    public int getOffloadingPort() {
        return offloadingPort;
    }

    public void setOffloadingPort(int offloadingPort) {
        this.offloadingPort = offloadingPort;
    }

    @Override
    public String toString() {
        return "Clone{" +
                "ip='" + ip + '\'' +
                ", profilerPort=" + profilerPort +
                ", offloadingPort=" + offloadingPort +
                '}';
    }
}
