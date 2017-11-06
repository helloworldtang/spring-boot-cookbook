package com.tangcheng.learning.string;

import org.junit.Test;

/**
 * @author tangcheng
 * 2017/11/06
 */
public class ReplaceFullWidthWhiteSpaceTest {


    @Test
    public void replaceSpecialWhiteSpaceTest() {


        System.out.println("============trim============");
        String input = "  金融/投资  证券";
        System.out.println("output:" + input.trim());
        System.out.println("============byte2char============");
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


}
