package com.tangcheng.app.api.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by tangcheng on 6/14/2017.
 */
@Profile("!prod")
@Configuration
public class DevConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SslConfig.class);

    static {
        LOGGER.info("下面这行代码将禁用SSL中的主机名检查。只在开发期间使用");
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
    }

}
