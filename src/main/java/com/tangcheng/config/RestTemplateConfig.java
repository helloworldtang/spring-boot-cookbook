package com.tangcheng.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by tang.cheng on 2016/10/19.
 */
@Configuration
@EnableConfigurationProperties(RedisConfig.class)
public class RestTemplateConfig {

    /**
     * http://docs.spring.io/spring-boot/docs/1.4.3.RELEASE/reference/htmlsingle/#boot-features-restclient-customization
     *
     * @return
     */
    public RestTemplate restTemplateOld() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClientBuilder.create().setConnectionTimeToLive(2, TimeUnit.SECONDS);
        HttpComponentsClientHttpRequestFactory requestFactory = new
                HttpComponentsClientHttpRequestFactory(httpClient);
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.2", 1234));
//        requestFactory.setProxy(proxy);
        requestFactory.setReadTimeout(2000);
        requestFactory.setConnectTimeout(2000);
        return new RestTemplate(requestFactory);
    }


    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;
    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = (60 * 1000);

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();

        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
                jsonConverter.setObjectMapper(objectMapper);
            }
        }

        return restTemplate;
    }

    @Bean
    public HttpClient httpClient() {

        PoolingClientConnectionManager connectionManager = new PoolingClientConnectionManager();
        HttpClient defaultHttpClient = new DefaultHttpClient(connectionManager);

        connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
        connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("facebook.com")), 20);
        connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("twitter.com")), 20);
        connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("linkedin.com")), 20);
        connectionManager.setMaxPerRoute(new HttpRoute(new HttpHost("viadeo.com")), 20);

        defaultHttpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, DEFAULT_READ_TIMEOUT_MILLISECONDS);
        return defaultHttpClient;
    }


}
