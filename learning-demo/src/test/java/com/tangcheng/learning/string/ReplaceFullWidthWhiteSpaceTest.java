package com.tangcheng.learning.string;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tangcheng
 * 2017/11/06
 */
public class ReplaceFullWidthWhiteSpaceTest {


    @Test
    public void replaceSpecialWhiteSpaceTest() {
        System.out.println(StringUtils.center("trim", 60, "="));
        String input = "  金融/投资  证券";
        System.out.println("output:" + input.trim());
        System.out.println(StringUtils.center("byte2char", 60, "="));
        for (char c : input.toCharArray()) {
            System.out.println(c + ",byte:" + (byte) c);
        }
        System.out.println(input.replace((char) 12288, 'a'));//网上找到的全角空格ascii(12288)
        /**
         * todo:为什么反过来转换成char，反而不行呢？
         */
        System.out.println(input.replace((char) -96, 'a'));//根据上面for遍历时，得到空白字符对应的ascii
        System.out.println(input.replaceAll(" ", ""));//直接把原始文本中的全角空格替换掉了
    }

    @Test
    public void replaceTest() {
        /**
         * replace会把两个都替换掉
         */
        String source = "H He Hel Hell=Hello-Hello+world";
        assertThat(source.replace("Hello", "")).isEqualTo("H He Hel Hell=-+world");
        /**
         * replaceFirst只替换第一个
         */
        assertThat(source.replaceFirst("Hello", "")).isEqualTo("H He Hel Hell=-Hello+world");
    }


}
