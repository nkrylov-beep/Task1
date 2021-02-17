import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for (; interfaces.hasMoreElements(); ) {
                NetworkInterface interFace = interfaces.nextElement();
                String interfaceName = interFace.getName();
                byte[] mac = interFace.getHardwareAddress();
                String sMac;
                if (mac != null) {
                    StringBuilder macBuilder = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                        macBuilder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    sMac = macBuilder.toString();
                } else
                    sMac = "NO";
                print(interfaceName);
                print("mac-address: " + sMac);
                Enumeration<InetAddress> inetAddresses = interFace.getInetAddresses();
                for (; inetAddresses.hasMoreElements(); ) {
                    String ip = inetAddresses.nextElement().getHostAddress();
                    print("ip-address: " + ip);
                }
                print("");
            }
        } catch (SocketException e) {
            print(e.getMessage());
        }
    }

    static void print(Object o) {
        System.out.println(o);
    }
}