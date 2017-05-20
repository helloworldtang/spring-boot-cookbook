package com.tangcheng.app.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

public class NetworkUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(NetworkUtil.class);

    public static final String UNKNOWN = "unknown";

    public static String getRemoteIp() {
        return getRemoteIp(RequestHolder.getRequestFacade());
    }

    public static String getRemoteIp(HttpServletRequest request) {
        try {
            return NetworkUtil.getIpAddress(request);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return UNKNOWN;
        }
    }

    private static String getIpAddress(HttpServletRequest request) throws IOException {
        String ipAddress = request.getHeader("X-Forwarded-For");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ipAddress:{}", ipAddress);
        }

        if (StringUtils.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ipAddress:{}", ipAddress);
                }
            }
            if (StringUtils.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ipAddress:{}", ipAddress);
                }
            }
            if (StringUtils.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("HTTP_CLIENT_IP");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ipAddress:{}", ipAddress);
                }
            }
            if (StringUtils.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ipAddress:{}", ipAddress);
                }
            }
            if (StringUtils.isBlank(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("getIpAddress(HttpServletRequest) - getRemoteAddr - String ipAddress:{}", ipAddress);
                }
            }
        } else if (ipAddress.contains(",")) {
            String[] ips = ipAddress.split(",");
            for (int index = ips.length - 1; index >= 0; index--) {
                if (!(UNKNOWN.equalsIgnoreCase(ips[index]))) {
                    ipAddress = ips[index];
                    break;
                }
            }
        }
        if (ipAddress.equals("0:0:0:0:0:0:0:1")) {//win7下使用localhost访问时没有经过路由
            return InetAddress.getLocalHost().getHostAddress();
        }
        return ipAddress;
    }

}  