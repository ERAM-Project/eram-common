package org.eram.common;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final String TAG = "ERAM-Utils";
    private static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                    + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    /**
     * Utility to execute a shell command on a Mac OS.
     *
     * @param command
     * @param asRoot
     * @param password
     * @return
     */
    public static String executeCommand(String command, boolean asRoot, String password) {

        Process p = null;
        String[] cmd = new String[]{"/bin/bash", "-c", command};

        try {
            if (asRoot) {
                cmd = new String[]{"/bin/bash", "-c", "echo " + password + "|sudo -S " + command};
            }

            System.out.println("Executing command: " + command);
            p = Runtime.getRuntime().exec(cmd);
            // you can pass the system command or a script to exec command.
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

            // read the output from the command
            StringBuilder sb = new StringBuilder();
            String s = "";
            while ((s = stdInput.readLine()) != null) {
                // System.out.println("Std OUT: "+s);
                sb.append(s);
            }

            while ((s = stdError.readLine()) != null) {
                System.out.println("Std ERROR : " + s);
            }

            System.out.println(sb.toString());

            stdInput.close();
            stdError.close();
            writer.close();

            return sb.toString();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    public static String executeCommand(String command) {
        return executeCommand(command, false, null);
    }

    public static byte[] serialize(Object obj) throws IOException {
        byte[] serializedObj;

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        try {
            o.writeObject(obj);
            o.flush();
            serializedObj = b.toByteArray();
        } finally {
            o.close();
        }

        return serializedObj;
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        return Utils.deserialize(bytes, 0, bytes.length);
    }

    public static Object deserialize(byte[] bytes, int offset, int length)
            throws IOException, ClassNotFoundException {
        Object obj;

        ByteArrayInputStream b = new ByteArrayInputStream(bytes, offset, length);
        ObjectInputStream o = new ObjectInputStream(b);
        obj = o.readObject();
        o.close();

        return obj;
    }


    public static void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    public static void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
            }
        }
    }

    public static void close(ObjectInputStream oIs) {
        if (oIs != null) {
            try {
                oIs.close();
            } catch (IOException e) {
            }
        }
    }

    public static void close(ObjectOutputStream oOs) {
        if (oOs != null) {
            try {
                oOs.close();
            } catch (IOException e) {
            }
        }
    }

    public static void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Validate ip address with regular expression
     *
     * @param ip ip address for validation
     * @return true valid ip address, false invalid ip address
     */
    public static boolean validateIpAddress(final String ip) {
        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
        return pattern.matcher(ip).matches();
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @return address or null
     */
    public static InetAddress getIpAddress() {

        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                // Log.i(TAG, "Interface: " + intf);
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    // Sokol: FIXME remove the hard coded "wlan" check
                    // Log.i(TAG, "IP: " + addr);
                    if (intf.getDisplayName().contains("wlan") && !addr.isLoopbackAddress()
                            // && InetAddressUtils.isIPv4Address(addr.getHostAddress())) {
                            && addr instanceof Inet4Address) {
                        return addr;
                    }
                    // On emulator
                    if (intf.getDisplayName().contains("eth0") && !addr.isLoopbackAddress()
                            // && InetAddressUtils.isIPv4Address(addr.getHostAddress())) {
                            && addr instanceof Inet4Address) {
                        return addr;
                    }
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Exception while getting IP address: " + e);
        }
        return null;
    }


    public static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        Pattern p = Pattern.compile("^[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)$");
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static String readAssetFileAsString(Context context, String fileName) throws IOException {
        InputStream is = context.getAssets().open(fileName);

        byte[] bytes = toByteArray(is);
        is.close();

        return new String(bytes, Charset.forName("UTF-8"));
    }

    public static byte[] toByteArray(InputStream in) throws IOException {
        // Presize the ByteArrayOutputStream since we know how large it will need
        // to be, unless that value is less than the default ByteArrayOutputStream
        // size (32).
        ByteArrayOutputStream out = new ByteArrayOutputStream(Math.max(32, in.available()));
        copy(in, out);
        return out.toByteArray();
    }

    public static long copy(InputStream from, OutputStream to) throws IOException {
        // checkNotNull(from);
        // checkNotNull(to);
        byte[] buf = createBuffer();
        long total = 0;
        while (true) {
            int r = from.read(buf);
            if (r == -1) {
                break;
            }
            to.write(buf, 0, r);
            total += r;
        }
        return total;
    }

    static byte[] createBuffer() {
        return new byte[8192];
    }
}
