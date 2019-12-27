package org.eram.common.settings;

public class ConnectionSettings {
    public static final int REMOTE_ERAM_PORT = 7543;
    public static final int REMOTE_PROFILER_PORT = 4543;

    public enum ConnectionType
    {
        CLEAR
    }

    public enum ExecutionLocation{
        LOCAL, REMOTE
    }
}
