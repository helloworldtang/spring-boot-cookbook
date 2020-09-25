/**
 * @Auther: cheng.tang
 * @Date: 2019/2/11
 * @Description:
 */
package com.tangcheng.learning.web.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
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
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: cheng.tang
 * @Date: 2019/2/11
 * @Description:
 */
@Slf4j
@Configuration
public class RestTemplateConfig {

    /**
     * 加入日志打印，方便排查问题
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(getHttpClient());
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (interceptors == null) {
            interceptors = Collections.singletonList(new LoggingClientHttpRequestInterceptor());
        } else {
            interceptors.add(new LoggingClientHttpRequestInterceptor());
        }
        restTemplate.setInterceptors(interceptors);
        /**
         * 404,500之类的报错。在LoggingClientHttpRequestInterceptor中都可以找到，就不再配置自定义ResponseErrorHandler了
         */
        return restTemplate;
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
        /**
         * 创建连接池管理器
         * PoolingHttpClientConnectionManager是一个HttpClientConnection的连接池，可以为多线程提供并发请求服务。主要作用就是分配连接，回收连接等。同一个route的请求，会优先使用连接池提供的空闲长连接。
         * https://www.cnblogs.com/shoren/p/httpclient-leaseConnection.html
         *
         * 2.7.4. Hostname verification
         * In addition to the trust verification and the client authentication performed on the SSL/TLS protocol level, HttpClient can optionally verify whether the target hostname matches the names stored inside the server's X.509 certificate, once the connection has been established. This verification can provide additional guarantees of authenticity of the server trust material. The javax.net.ssl.HostnameVerifier interface represents a strategy for hostname verification. HttpClient ships with two javax.net.ssl.HostnameVerifier implementations. Important: hostname verification should not be confused with SSL trust verification.
         *
         * DefaultHostnameVerifier:  The default implementation used by HttpClient is expected to be compliant with RFC 2818. The hostname must match any of alternative names specified by the certificate, or in case no alternative names are given the most specific CN of the certificate subject. A wildcard can occur in the CN, and in any of the subject-alts.
         * NoopHostnameVerifier:  This hostname verifier essentially turns hostname verification off. It accepts any SSL session as valid and matching the target host.
         * Per default HttpClient uses the DefaultHostnameVerifier implementation. One can specify a different hostname verifier implementation if desired
         *
         * SSLContext sslContext = SSLContexts.createSystemDefault();
         * SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
         *         sslContext,
         *         NoopHostnameVerifier.INSTANCE);
         *
         * 2.7.2. Integration with connection manager
         * Custom connection socket factories can be associated with a particular protocol scheme as as HTTP or HTTPS and then used to create a custom connection manager.
         *
         * ConnectionSocketFactory plainsf = <...>
         * LayeredConnectionSocketFactory sslsf = <...>
         * Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create()
         *         .register("http", plainsf)
         *         .register("https", sslsf)
         *         .build();
         *
         * HttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(r);
         * HttpClients.custom()
         *         .setConnectionManager(cm)
         *         .build();
         * http://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/connmgmt.html#d5e418
         *
         */
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
                    closeableHttpClient.close();
                    log.info("http client closed");
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        });
        return closeableHttpClient;
    }


}


@Slf4j
class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final String UNKNOWN_HTTP_METHOD = "[unknown httpMethod]";

    private static final String HAS_NO_CONTENT_TYPE = "[has no contentType]";


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        long begin = System.currentTimeMillis();
        String httpMethodName = UNKNOWN_HTTP_METHOD;
        HttpMethod method = request.getMethod();
        if (method != null) {
            httpMethodName = method.name();
        }
        String requestBody = IOUtils.toString(body, "utf-8");
        try {
            ClientHttpResponse response = execution.execute(request, body);
            long cost = System.currentTimeMillis() - begin;

            MediaType contentType = response.getHeaders().getContentType();
            String responseContent;
            if (contentType != null) {
                String subtype = contentType.getSubtype();
                if (subtype.contains("json") || contentType.getType().contains("text") || subtype.contains("xml")) {
                    responseContent = IOUtils.toString(response.getBody(), "utf-8");
                } else {
                    responseContent = "neither text,nor xml,nor json";
                }
            } else {
                responseContent = HAS_NO_CONTENT_TYPE;
            }

            if (cost > 3000) {
                log.info("【特殊标识】慢接口 【 TODOLIST 】 大于3秒 cost:[{}]ms,{} {}. request:{},response:{},", cost, httpMethodName, request.getURI().toString(), requestBody, responseContent);
            } else if (cost > 2000) {
                log.info("【特殊标识】慢接口 【 TODOLIST 】 大于2秒 cost:[{}]ms,{} {}. request:{},response:{},", cost, httpMethodName, request.getURI().toString(), requestBody, responseContent);
            } else {
                log.info("cost:[{}]ms,on {} {}. request:{},response:{},", cost, httpMethodName, request.getURI().toString(), requestBody, responseContent);
            }
            return response;
        } catch (IOException e) {
            log.error("【特殊标识】【 TODOLIST 】 接口 cost:[{}]ms,I/O error on {} {}. request:{},response:{},", (System.currentTimeMillis() - begin), httpMethodName, request.getURI().toString(), requestBody, e.getMessage());
            throw e;
        }

    }

}

