/**
 * @Auther: cheng.tang
 * @Date: 2019/1/26
 * @Description:
 */
package com.tangcheng.learning.serialize;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Auther: cheng.tang
 * @Date: 2019/1/26
 * @Description:
 */
public class JacksonTest {


    @Test
    public void testSerialize() throws IOException {
        String source = "{\"NaMe\":\"hello\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        NameReq nameReq = objectMapper.readValue(source, NameReq.class);
        assertThat(nameReq.getName()).isEqualTo("hello");
        String labelJson = objectMapper.writeValueAsString(nameReq);
        System.out.println(labelJson);//{"NaMe":"hello"}
        assertThat(labelJson).isEqualTo(source);
    }


    @Data
    public static class NameReq {

        @JsonProperty(value = "NaMe")
        public String name;

    }


}
