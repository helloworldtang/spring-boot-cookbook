package com.tangcheng.learning.util.template;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class TemplateTest {
    private String hi = "hi,";
    private String template = hi + "${name}";
    private String expected;
    private Map<String, String> params;

    @Before
    public void setup() {
        String name = "tangcheng";
        expected = hi + name;
        params = new HashMap<>();
        params.put("name", name);

    }

    @Test
    public void Should_Given_Key_Value_When_Return_Human_Content_Use_Builder() {
        String process = Template.builder().template(template).build().process(params);
        assertThat(process).isEqualTo(expected);
    }

    @Test
    public void Should_Given_Key_Value_When_Return_Human_Content_Use_Static_Process() {
        String process = Template.process(template, params);
        assertThat(process).isEqualTo(expected);
    }
}