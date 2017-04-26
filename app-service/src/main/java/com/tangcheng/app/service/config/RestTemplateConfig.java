package com.tangcheng.app.service.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by tang.cheng on 2016/10/19.
 */
@Configuration
public class RestTemplateConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateConfig.class);

    @Bean
    public RestTemplate restTemplate() {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(getHttpClient());
        return new RestTemplate(requestFactory);
    }

    //httpclient 4.5.2使用连接池的经典配置
    private CloseableHttpClient getHttpClient() {
        //注册访问协议相关的Socket工厂
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        //HttpConnectionFactory:配置写请求/解析响应处理器
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory = new ManagedHttpClientConnectionFactory(
                DefaultHttpRequestWriterFactory.INSTANCE,
                DefaultHttpResponseParserFactory.INSTANCE
        );

        //DNS解析器
        DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
        //创建连接池管理器
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connectionFactory, dnsResolver);
        //设置默认的socket参数
        manager.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());
        manager.setMaxTotal(300);//设置最大连接数。高于这个值时，新连接请求，需要阻塞，排队等待
        //路由是对MaxTotal的细分。
        // 每个路由实际最大连接数默认值是由DefaultMaxPerRoute控制。
        // MaxPerRoute设置的过小，无法支持大并发：ConnectionPoolTimeoutException:Timeout waiting for connection from pool
        manager.setDefaultMaxPerRoute(200);//每个路由的最大连接
        manager.setValidateAfterInactivity(5 * 1000);//在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证，默认为2s

        //配置默认的请求参数
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(2 * 1000)//连接超时设置为2s
                .setSocketTimeout(5 * 1000)//等待数据超时设置为5s
                .setConnectionRequestTimeout(2 * 1000)//从连接池获取连接的等待超时时间设置为2s
//                .setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.2", 1234))) //设置代理
                .build();

        CloseableHttpClient closeableHttpClient = HttpClients.custom()
                .setConnectionManager(manager)
                .setConnectionManagerShared(false)//连接池不是共享模式，这个共享是指与其它httpClient是否共享
                .evictIdleConnections(60, TimeUnit.SECONDS)//定期回收空闲连接
                .evictExpiredConnections()//回收过期连接
                .setConnectionTimeToLive(60, TimeUnit.SECONDS)//连接存活时间，如果不设置，则根据长连接信息决定
                .setDefaultRequestConfig(defaultRequestConfig)//设置默认的请求参数
                .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)//连接重用策略，即是否能keepAlive
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)//长连接配置，即获取长连接生产多长时间
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))//设置重试次数，默认为3次；当前是禁用掉
                .build();

        /**
         *JVM停止或重启时，关闭连接池释放掉连接
         */
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    LOGGER.info("closing http client");
                    closeableHttpClient.close();
                    LOGGER.info("http client closed");
                } catch (IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        });
        return closeableHttpClient;
    }


}
