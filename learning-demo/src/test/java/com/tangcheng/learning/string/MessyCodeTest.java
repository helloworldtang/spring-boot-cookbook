package com.tangcheng.learning.string;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/08/27 19:03
 */
public class MessyCodeTest {


    /**
     * https://www.cnblogs.com/softidea/p/4252698.html
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test1() throws UnsupportedEncodingException {
        String str = "我不是乱码";
        String str3 = new String(str.getBytes("GBK"), "UTF-8");
        String str4 = new String(str3.getBytes("UTF-8"), "GBK");//这里你并不知道数据已经破坏了，这样用是对的。
        System.out.println(str4);//锟斤拷
    }


    @Test
    public void test2() throws UnsupportedEncodingException {
        String str = "我不是乱码";
        String str3 = new String(str.getBytes("UTF-8"), "GBK");//这里你并不知道数据已经破坏了，这样用是对的。
        String str4 = new String(str3.getBytes("GBK"), "UTF-8");
        System.out.println(str4);//我不是乱�?
    }


}
