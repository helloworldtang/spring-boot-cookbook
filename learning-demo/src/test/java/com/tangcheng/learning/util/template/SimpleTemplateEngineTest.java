package com.tangcheng.learning.util.template;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTemplateEngineTest {

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
        String process = SimpleTemplateEngine.builder().template(template).build().process(params);
        assertThat(process).isEqualTo(expected);
    }


    @Test
    public void Should_Given_Key_Value_When_Return_Human_Content_Use_Static_Process() {
        String process = SimpleTemplateEngine.process(template, params);
        assertThat(process).isEqualTo(expected);
    }


    /**
     * java.lang.IllegalArgumentException: character to be escaped is missing
     * <p>
     * at java.util.regex.Matcher.appendReplacement(Matcher.java:809)
     * at java.util.regex.Matcher.replaceAll(Matcher.java:955)
     * at java.lang.String.replaceAll(String.java:2223)
     */
    @Test
    public void whenReplacementWith_backslash_thenSuccess() {
        String name = "tangcheng\\";
        System.out.println(name);
        String expected = hi + name;
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        String result = SimpleTemplateEngine.process(template, params);
        System.out.println(result);
        assertThat(result).isEqualTo(expected);
    }

    /**
     * java.lang.IllegalArgumentException: Illegal group reference
     * <p>
     * at java.util.regex.Matcher.appendReplacement(Matcher.java:857)
     * at java.util.regex.Matcher.replaceAll(Matcher.java:955)
     * at java.lang.String.replaceAll(String.java:2223)
     */
    @Test
    public void whenReplacementWith_$_thenSuccess() {
        String name = "$tangcheng";
        System.out.println(name);
        String expected = hi + name;
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        String result = SimpleTemplateEngine.process(template, params);
        System.out.println(result);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenReplacementWith_$_Or_backslash_thenSuccess() {
        String name = "$tangcheng\\";
        System.out.println(name);
        String expected = hi + name;
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        String result = SimpleTemplateEngine.process(template, params);
        System.out.println(result);
        assertThat(result).isEqualTo(expected);
    }


}