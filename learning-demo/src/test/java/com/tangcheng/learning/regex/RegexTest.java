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


    /**
     * 很多正则引擎都支持命名分组，java是在java7中才引入这个特性，语法与.Net类似（.Net允许同一表达式出现名字相同的分组，java不允许）。
     * 命名分组很好理解，就是给分组进行命名。下面简单演示一下java中如何使用以及注意事项。
     * 1.正则中定义名为NAME的分组
     * (?<NAME>X)
     * 这里X为我们要匹配的内容，注意，在这个命名不能重复，名字也不能以数字开头！
     * 2.反向引用NAME组所匹配到的内容
     * \k<NAME>
     * 注意，反向引用是针对组所匹配到的内容，而非组的表达式。
     * 3.替换中，引用组NAME中捕获到的字符串
     * ${NAME}
     * 4.获取NAME组捕获的字符串
     * group(String NAME)
     * 注意：也可以使用序号对命名捕获进行引用，序号从1开始，0为正则的完整匹配结果。
     * https://www.cnblogs.com/softidea/p/5312413.html
     */
    @Test
    public void testGroupName() {
        String s = "测试日期 2015-10-26 ，对的";
        Pattern p = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})");
        Matcher m = p.matcher(s);
        if (m.find()) {
            System.out.println("year: " + m.group("year")); //年
            System.out.println("month: " + m.group("month")); //月
            System.out.println("day: " + m.group("day")); //日

            System.out.println("匹配到全部的内容 : " + m.group(0)); //第一组
            System.out.println("year: " + m.group(1)); //第一组
            System.out.println("month: " + m.group(2)); //第二组
            System.out.println("day: " + m.group(3)); //第三组
        }
        System.out.println(s.replaceAll("(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})", "${day}-${month}-${year}")); //将 年-月-日 形式的日期改为 日-月-年 形式
    }


}
