package com.addlove.service.goods.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 网络相关工具类
 * @author lw
 *
 */
public class NetUtil {

    /**
     * 本机ip地址
     */
    private static String sCACHEIP;

    /**
     * 获取本机IP，优先获取外网IP，如没有外网IP则返回第一个IP
     *
     * @return 本机ip地址
     */
    public static String getLocalIp() {
        if (sCACHEIP != null) {
            return sCACHEIP;
        }
        try {
            String firstIp = null;
            String internetIp = null;
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                if (!netInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address && !ip.isLoopbackAddress()) {
                        if (ip.isSiteLocalAddress()) {
                            if (firstIp == null) {
                                firstIp = ip.getHostAddress();
                            }
                        } else {
                            internetIp = ip.getHostAddress();
                            break;
                        }
                    }
                }
            }
            sCACHEIP = (internetIp == null ? firstIp : internetIp);
            return sCACHEIP;
        } catch (SocketException e) {
            return null;
        }
    }
}
