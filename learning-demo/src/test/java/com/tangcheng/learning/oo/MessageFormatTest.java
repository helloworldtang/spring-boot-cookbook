package com.tangcheng.learning.oo;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Date;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-07  14:53
 */
public class MessageFormatTest {

    @Test
    public void main() throws Exception {
        Object[] arguments = {
                7,
                new Date(System.currentTimeMillis()),
                "a disturbance in the Force"
        };

        //MessageFormat具备格式化的功能
        String result = MessageFormat.format(
                "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
                arguments);
        System.out.println(result);
        System.out.println("Long和Date类型的数据，在输出时会被格式化。保守的做法，就是把参数都转换成String");
        Integer num = 1234567890;
        String longStyle = MessageFormat.format("expect:1234567890.but:{0}.str:{1}", num, num.toString());
//        expect:1234567890.but:1,234,567,890.str:1234567890
        System.out.println(longStyle);
    }

}