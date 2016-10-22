package com.rest;

import com.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by tang.cheng on 2016/10/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RestTemplateDemoControllerTest {

    String domain = "http://localhost:9999";

    @Autowired
    RestTemplateDemoController restTemplateDemoController;
    //待测试的服务需要先启动，否则会提示 Connect to localhost:9999 [localhost/127.0.0.1, localhost/0:0:0:0:0:0:0:1] failed: Connection refused: connect

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGetPathVariable() {
        String url = domain + "/user/{userId}";
        MultiValueMap<String, Integer> urlVariables = new LinkedMultiValueMap<>();
        String userId = String.valueOf(1);
        ResponseEntity<Result> forEntity = restTemplate.getForEntity(url, Result.class, userId);
        Result body = forEntity.getBody();
        assertThat(body.getUserId(), is(userId));
    }
}
