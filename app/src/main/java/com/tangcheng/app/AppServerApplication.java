/**
 * @Auther: cheng.tang
 * @Date: 2019/8/6
 * @Description:
 */
package com.tangcheng.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: cheng.tang
 * @date: 2019/8/6
 * @see
 * @since
 */
@SpringBootApplication
@MapperScan("com.tangcheng.app.dao.repository.mapper")
public class AppServerApplication {
    /**
     * Spring Boot uses a very particular PropertySource order that is designed to allow sensible overriding of values, properties are considered in the following order:
     * <p>
     * Command line arguments.
     * Properties from SPRING_APPLICATION_JSON (inline JSON embedded in an environment variable or system property)
     * JNDI attributes from java:comp/env.
     * Java System properties (System.getProperties()).
     * OS environment variables.
     * A RandomValuePropertySource that only has properties in random.*.
     * Profile-specific application properties outside of your packaged jar (application-{profile}.properties and YAML variants)
     * Profile-specific application properties packaged inside your jar (application-{profile}.properties and YAML variants)
     * Application properties outside of your packaged jar (application.properties and YAML variants).
     * Application properties packaged inside your jar (application.properties and YAML variants).
     * <p>
     * java -jar app.jar --name="Spring").
     * <p>
     * [Tip]
     * The SPRING_APPLICATION_JSON properties can be supplied on the command line with an environment variable. For example in a UN*X shell:
     * $ SPRING_APPLICATION_JSON='{"foo":{"bar":"spam"}}' java -jar myapp.jar
     * In this example you will end up with foo.bar=spam in the Spring Environment. You can also supply the JSON as spring.application.json in a System variable:
     * $ java -Dspring.application.json='{"foo":"bar"}' -jar myapp.jar
     * or command line argument:
     * $ java -jar myapp.jar --spring.application.json='{"foo":"bar"}'
     * <p>
     * <p>
     * <p>
     * It is also possible to run a packaged application with remote debugging support enabled. This allows you to attach a debugger to your packaged application:
     * <p>
     * $ java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n  -jar target/myproject-0.0.1-SNAPSHOT.jar
     * http://docs.spring.io/spring-boot/docs/1.3.9.BUILD-SNAPSHOT/reference/htmlsingle/#using-boot-devtools
     *
     * @param args
     * @PropertySource annotations on your @Configuration classes.
     * Default properties (specified using SpringApplication.setDefaultProperties).
     */
    public static void main(String[] args) {
        System.setProperty("-Xms", "200m");
        System.setProperty("-Xmx", "200m");
        SpringApplication.run(AppServerApplication.class, args);
    }

}
