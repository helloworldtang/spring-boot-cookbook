package com.tangcheng.learning.web.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * As well as mocking the HTTP request cycle we can also use Spring Boot to write a very simple full-stack integration test.
 * https://spring.io/guides/gs/spring-boot/
 *
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/11/06 15:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {

    /**
     * The embedded server is started up on a random port by virtue of the webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
     * and the actual port is discovered at runtime with the @LocalServerPort.
     */
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/v1/hello");
    }

    @Test
    public void getHello() {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), equalTo(HelloController.GREETINGS_FROM_SPRING_BOOT));
    }


}
