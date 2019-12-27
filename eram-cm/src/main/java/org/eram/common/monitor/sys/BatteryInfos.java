package org.eram.common.monitor.sys;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BatteryInfos {

    private final static String SYS_CLASS_POWER = "/sys/class/power_supply";
    private final static String BATTERY = "/battery";
    private final static String VOLTAGE = "/batt_vol";
    private final static String VOLTAGE_ALT = "/voltage_now";

    /**
     * Read current battery voltage from /sys/class/power_supply/battery/batt_vol or
     * /sys/class/power_supply/battery/voltage_now - try both files since it is done in the battery
     * service of Android, so must be model/version dependent
     */
    public static Long getCurrentVoltage() {
        StringBuilder sb = new StringBuilder();
        sb.append(SYS_CLASS_POWER).append(BATTERY).append(VOLTAGE);
        Long result = readLong(sb.toString());
        if (result != -1)
            return result;
        else {
            sb = new StringBuilder();
            sb.append(SYS_CLASS_POWER).append(BATTERY).append(VOLTAGE_ALT);
            result = readLong(sb.toString());
            return result;
        }

    }

    private static RandomAccessFile getFile(String filename) throws IOException {
        File f = new File(filename);
        return new RandomAccessFile(f, "r");
    }

    private static long readLong(String file) {
        RandomAccessFile raf = null;
        try {
            raf = getFile(file);
            return Long.valueOf(raf.readLine());
        } catch (Exception e) {
            // Log.d(TAG, "Could not read voltage: " + e);
            return -1;
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
