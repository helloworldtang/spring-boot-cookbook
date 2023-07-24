package com.tangcheng.app.core.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

/**
 * @Auther: cheng.tang
 * @Date: 2017/5/20
 * @Description:
 */
public class NetworkUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(NetworkUtil.class);

    public static final String UNKNOWN = "unknown";

    public static String getRemoteIp() {
        return getRemoteIp(RequestHolder.getRequestFacade());
    }

    /**
     * todo:  user-agent长度范围，如何是合适的区间？
     *
     * @return
     */
    public static String getUserAgent() {
        return RequestHolder.getRequestFacade().getHeader(HttpHeaders.USER_AGENT);
    }

    public static String getRemoteIp(HttpServletRequest request) {
        try {
            return NetworkUtil.getIpAddress(request);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return UNKNOWN;
        }
    }

    /**
     * 对trace/debug/info级别的日志输出，必须使用条件输出形式或者使用占位符的方式
     * LOGGER.info("symbol:"+symbol);
     * 日志级别是WARN，上述日志不会打印，但是会执行字符串拼接操作，
     * 如果symbol是对象，会执行toString()方法，浪费系统资源，执行了上述操作，最终日志却没有打印
     *
     * @param request
     * @return
     * @throws IOException
     */
    private static String getIpAddress(HttpServletRequest request) throws IOException {
        String ip = request.getHeader("X-Forwarded-For");

        LOGGER.debug("X-Forwarded-For {}", ip);

        if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
                LOGGER.debug("Proxy-Client-IP {}", ip);
            }
            if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                LOGGER.debug("WL-Proxy-Client-IP {}", ip);
            }
            if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
                LOGGER.info("HTTP_CLIENT_IP {}", ip);
            }
            if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                LOGGER.debug("HTTP_X_FORWARDED_FOR {}", ip);
            }
            if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                LOGGER.debug("getRemoteAddr {}", ip);
            }
        } else if (ip.contains(",")) {
            String[] ips = ip.split(",");
            for (int index = ips.length - 1; index >= 0; index--) {
                if (!(UNKNOWN.equalsIgnoreCase(ips[index]))) {
                    ip = ips[index];
                    break;
                }
            }
        }
        if (Objects.equals(ip, "0:0:0:0:0:0:0:1")) {//win7下使用localhost访问时没有经过路由
            return InetAddress.getLocalHost().getHostAddress();
        }
        if (StringUtils.isBlank(ip)) {
            return UNKNOWN;
        }
        return ip;
    }

}  