package com.tangcheng.learning.util.template;

import lombok.Builder;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.regex.Matcher;

@Builder
public class Template {

    private String template;

    /**
     * 简单实现${}模板功能
     * 如${var1} content ${var2} 其中 ${var1}, ${var2} 为占位符. 可用相关变量进行替换
     *
     * @param params 需要被替换的变量名和变量值之间的映射关系
     * @return
     */
    public String process(Map<String, String> params) {
        return process(this.template, params);
    }

    /**
     * 简单实现${}模板功能
     * 如${var1} content ${var2} 其中 ${var1}, ${var2} 为占位符. 可用相关变量进行替换
     *
     * @param template 模板字符串
     * @param params   需要被替换的变量名和变量值之间的映射关系
     * @return
     */
    public static String process(String template, Map<String, String> params) {
        Assert.notNull(template, "template must not be null");
        Assert.notEmpty(params, "params must not be empty");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String replacement = entry.getValue();
            replacement = Matcher.quoteReplacement(replacement);
            template = template.replaceAll("\\$\\{".concat(entry.getKey().trim()).concat("\\}"), replacement);
        }
        return template;
    }

}
