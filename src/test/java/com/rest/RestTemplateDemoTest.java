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
public class RestTemplateDemoTest {

    String domain = "http://localhost:9999";
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testGetPathVariable() {
        String url = domain + "/user/{userId}";
        MultiValueMap<String, Integer> urlVariables = new LinkedMultiValueMap<>();
        int expected = 1;
        urlVariables.add("userId", expected);
        ResponseEntity<Result> forEntity = restTemplate.getForEntity(url, Result.class, urlVariables);
        Result body = forEntity.getBody();
        assertThat(body.getUserId(), is(expected));
    }
}
