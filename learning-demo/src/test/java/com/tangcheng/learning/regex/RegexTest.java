package com.tangcheng.learning.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tangcheng
 * 2017/10/30
 * 【强制】在使用正则表达式时，利用好其预编译功能，可以有效加快正则匹配速度。
 * 说明：不要在方法体内定义：Pattern pattern = Pattern.compile(规则);
 */
public class RegexTest {


    @Test
    public void ExtractRegexTest() {
        String strInput = "3a7s9-8p10@5d2a6s17s56;33";
        String regEx = "[^0-9]";//匹配指定范围内的数字

        //Pattern是一个正则表达式经编译后的表现模式
        Pattern p = Pattern.compile(regEx);

        // 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
        Matcher m = p.matcher(strInput);

        //将输入的字符串中非数字部分用空格取代并存入一个字符串
        String string = m.replaceAll(" ").trim();

        //以空格为分割符在讲数字存入一个字符串数组中
        String[] strArr = string.split(" ");

        //遍历数组转换数据类型输出
        for (String s : strArr) {
            System.out.println(Integer.parseInt(s));
        }

    }


    @Test
    public void ExtractRegexAllTest() {
        String strInput = "3a7s9-8p10@5d2a6s17s56;33";
        String regEx = "([0-9]{1,3})";//匹配指定范围内的数字
        //Pattern是一个正则表达式经编译后的表现模式
        Pattern pattern = Pattern.compile(regEx);
        // 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
        Matcher matcher = pattern.matcher(strInput);
        while (matcher.find()) {
            /**
             * 捕获组也就是Pattern中以括号对“()”分割出的子Pattern。至于为什么要用捕获组呢，主要是为了能找出在一次匹配中你更关心的部分。
             * 组零始终代表整个表达式。之所以这样命名捕获组是因为在匹配中，保存了与这些组匹配的输入序列的每个子序列。
             * http://www.cnblogs.com/softidea/p/3922002.html
             */
            System.out.println(matcher.group(1));
        }
    }


}
