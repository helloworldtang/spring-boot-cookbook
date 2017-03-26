package com.tangcheng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by tang.cheng on 2016/10/19.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * http://docs.spring.io/spring-boot/docs/1.4.3.RELEASE/reference/htmlsingle/#boot-features-restclient-customization
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.2", 1234));
//        requestFactory.setProxy(proxy);
        requestFactory.setReadTimeout(2000);
        requestFactory.setConnectTimeout(2000);
        return new RestTemplate(requestFactory);
    }

}
