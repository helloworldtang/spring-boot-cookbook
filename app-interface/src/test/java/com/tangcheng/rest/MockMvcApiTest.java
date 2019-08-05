package com.tangcheng.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangcheng.app.InterfaceApplication;
import com.tangcheng.app.domain.query.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by MyWorld on 2016/10/19.
 */

/*Spring Boot的SpringApplicationConfiguration注解在Spring Boot 1.4开始，被标记为Deprecated
解决：替换为SpringBootTest即可*/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InterfaceApplication.class)
@WebAppConfiguration
public class MockMvcApiTest {

    @Autowired
    WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;
    private String expectedJson;


    @Before
    public void setUp() throws JsonProcessingException {
        Result result = new Result("1");
        expectedJson = obj2Json(result);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private String obj2Json(Result result) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(result);
    }

    @Test
    public void testUserInfo() throws Exception {
        String uri = "/user/1";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertThat("正确的返回值为200", 200, is(status));
        String content = result.getResponse().getContentAsString();
        assertThat(content, is(expectedJson));
    }


}
