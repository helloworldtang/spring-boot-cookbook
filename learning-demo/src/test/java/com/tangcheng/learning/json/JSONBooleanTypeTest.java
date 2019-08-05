package com.tangcheng.learning.json;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/08/03 16:31
 */
public class JSONBooleanTypeTest {

    @Test
    public void testFastJson() {
        BooleanType booleanType = new BooleanType();
        booleanType.setEnable(true);
        String output = JSON.toJSONString(booleanType);
        assertThat(output).isEqualTo("{\"enable\":true}");

        /**
         * 字符串的值除了1和true外，都是false
         */
        BooleanType serialObj = JSON.parseObject("{\"enable\":true}", BooleanType.class);
        assertThat(serialObj.getEnable()).isTrue();
        serialObj = JSON.parseObject("{\"enable\":1}", BooleanType.class);
        assertThat(serialObj.getEnable()).isTrue();

        serialObj = JSON.parseObject("{\"enable\":false}", BooleanType.class);
        assertThat(serialObj.getEnable()).isFalse();
        serialObj = JSON.parseObject("{\"enable\":2}", BooleanType.class);
        assertThat(serialObj.getEnable()).isFalse();
        serialObj = JSON.parseObject("{\"enable\":0}", BooleanType.class);
        assertThat(serialObj.getEnable()).isFalse();

        serialObj = JSON.parseObject("{\"enable\":-1}", BooleanType.class);
        assertThat(serialObj.getEnable()).isFalse();
    }

    @Data
    public static class BooleanType {
        private Boolean enable;
    }
}
