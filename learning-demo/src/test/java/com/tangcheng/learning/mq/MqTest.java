package com.tangcheng.learning.mq;

import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: cheng.tang
 * @date: 2020/9/9
 * @see
 * @since
 */
public class MqTest {

    @Test
    public void testNumberMqMsg() throws IOException {
        String msg = "{\"name\":\"mq\",\"type\":1}";
        byte[] msgByte = msg.getBytes(StandardCharsets.UTF_8.name());
        String msgByteStr = Arrays.stream(ObjectUtils.toObjectArray(msgByte)).map(String::valueOf).collect(Collectors.joining(","));
        String[] msgBytes = msgByteStr.split(",");
        byte[] bytes = new byte[msgBytes.length];
        for (int i = 0; i < msgBytes.length; i++) {
            bytes[i] = Byte.parseByte(msgBytes[i]);
        }
        assertThat(msg).isEqualTo(new String(bytes, StandardCharsets.UTF_8.name()));
    }


}
