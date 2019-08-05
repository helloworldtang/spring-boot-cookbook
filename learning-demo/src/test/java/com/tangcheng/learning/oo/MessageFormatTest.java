package com.tangcheng.learning.oo;

import org.junit.Test;

import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * spring-boot-cookbook
 * https://www.cnblogs.com/softidea/p/5530834.html
 *
 * @author : tang.cheng
 * @version : 2017-07-07  14:53
 */
public class MessageFormatTest {

    @Test
    public void shouldReturnData() {
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

    @Test
    public void test() {
        Locale locale = Locale.getDefault(Locale.Category.FORMAT);
        Format subFormatter = NumberFormat.getInstance(locale);
        int number = 1234567890;
        String numberFormat = ((NumberFormat) subFormatter).format(number);
        System.out.println(MessageFormat.format("{0},number result:{1}", number, numberFormat));//1,234,567,890,number result:1,234,567,890

        subFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);//fix
        Date now = new Date();
        String dateFormatResult = ((DateFormat) subFormatter).format(now);
        System.out.println(MessageFormat.format("now:{0},now:{1}", now, dateFormatResult)); //now:18-6-12 下午12:19,now:18-6-12 下午12:19

    }


}