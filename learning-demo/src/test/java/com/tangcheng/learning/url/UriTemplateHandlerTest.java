package com.tangcheng.learning.url;

import org.junit.Test;
import org.springframework.web.util.DefaultUriTemplateHandler;
import org.springframework.web.util.UriTemplateHandler;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: cheng.tang
 * @date: 2020/4/23
 * @see
 * @since
 */
public class UriTemplateHandlerTest {

    @Test
    public void uriTemplateHandlerTest() {
        UriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("orderNo", "12345");
        URI expand = uriTemplateHandler.expand("https://chaojihao.com/user/order/detail?orderno={orderNo}", uriVariables);
        System.out.println(expand.toString());
        assertThat(expand.toString()).isEqualTo("https://chaojihao.com/user/order/detail?orderno=12345");
    }


}
