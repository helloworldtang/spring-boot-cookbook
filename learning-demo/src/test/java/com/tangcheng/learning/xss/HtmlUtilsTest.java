/**
 * @Auther: cheng.tang
 * @Date: 2019/6/27
 * @Description:
 */
package com.tangcheng.learning.xss;

import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Auther: cheng.tang
 * @Date: 2019/6/27
 * @Description:
 */
public class HtmlUtilsTest {

    /**
     * 处理xss的要点，把可能用到html标签都转义掉
     * 譬如 xss中最有杀伤力的js，那把 <, > 这两个关键标识转义掉，那就没有xss了
     * 元字符   转义后的字符
     * &           &amp;
     * <   &lt;
     * > &gt;
     * " &quot;
     * ' &#27;
     *
     */
    @Test
    public void htmlEscapeTest() {
        System.out.println(StandardCharsets.UTF_8.displayName());
        String source = "< script > </script >";
        String result = HtmlUtils.htmlEscape(source, StandardCharsets.UTF_8.displayName());
        System.out.println(result);
        assertThat(result).isEqualTo("&lt; script &gt; &lt;/script &gt;");
        assertThat(HtmlUtils.htmlUnescape(result)).isEqualTo(source);
    }


}
