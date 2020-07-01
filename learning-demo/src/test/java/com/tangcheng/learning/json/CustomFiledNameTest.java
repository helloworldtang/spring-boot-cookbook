package com.tangcheng.learning.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Java序列化就是指把Java对象转换为字节序列的过程
 * Java反序列化就是指把字节序列恢复为Java对象的过程
 *
 * @author: cheng.tang
 * @date: 2020/7/1
 * @see
 * @since
 */
@Data
public class CustomFiledNameTest {

    private CustomField customField;

    @Before
    public void setUp() {
        customField = new CustomField();
        customField.setFastJsonCustomField("fastJsonCustomField");
        customField.setJacksonCustomField("jacksonCustomField");
        customField.setTwoTypeCustomField("twoTypeCustomField");
    }

    @Test
    public void ObjectMapperSceneTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String actual = objectMapper.writeValueAsString(customField);
        /**
         * expected：加了@JsonProperty的序列化结果中的字段名都是自定义的那个
         */
        String expected = "{\"fastJsonCustomField\":\"fastJsonCustomField\",\"jacksonFormatOnly\":\"jacksonCustomField\",\"jacksonFormatWithTwo\":\"twoTypeCustomField\"}";
        /**
         * 检查序列化时 JsonProperty时是否有用
         */
        assertThat(actual).isEqualTo(expected);

        CustomField customField = objectMapper.readValue(actual, CustomField.class);
        /**
         * 反序列化场景
         */
        assertThat(Objects.equals(getCustomField(), customField)).isTrue();

        /**
         * 序列化场景
         */
        assertThat(Objects.equals(actual, objectMapper.writeValueAsString(customField))).isTrue();
    }


    /**
     * 发现fastjson和jackson序列化java对象时，字段的顺序不同
     */
    @Test
    public void fastJsonSceneTest() {
        String actual = JSON.toJSONString(customField);
        System.out.println(actual);
        /**
         * expected：加了@JSONField的序列化结果中的字段名都是自定义的那个
         */
        String expected = "{\"fastJsonFormatOnly\":\"fastJsonCustomField\",\"fastJsonFormatWithTwo\":\"twoTypeCustomField\",\"jacksonCustomField\":\"jacksonCustomField\"}";
        /**
         * 检查序列化时 @JSONField时是否有用
         */
        assertThat(actual).isEqualTo(expected);

        CustomField customField = JSON.parseObject(actual, CustomField.class);
        /**
         * 反序列化场景
         */
        assertThat(Objects.equals(getCustomField(), customField)).isTrue();

        /**
         * 序列化场景
         */
        assertThat(Objects.equals(actual, JSON.toJSONString(customField))).isTrue();
    }


}

@Data
class CustomField {

    @JsonProperty("jacksonFormatOnly")
    private String jacksonCustomField;

    @JSONField(name = "fastJsonFormatOnly")
    private String fastJsonCustomField;

    @JsonProperty("jacksonFormatWithTwo")
    @JSONField(name = "fastJsonFormatWithTwo")
    private String twoTypeCustomField;

}
