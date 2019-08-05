package com.tangcheng.learning.xml;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

/**
 * @author tangcheng
 * 2017/11/15
 */
public class JAXBTest {


    @Test
    public void test() throws JAXBException {
//        https://www.cnblogs.com/Nouno/p/5728112.html
        JAXBContext context = JAXBContext.newInstance(Boy.class);

        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Boy boy = new Boy();
//        marshall成 xml文件
        marshaller.marshal(boy, System.out);
        System.out.println("\n=============");

        String xml = "<boy><name>David</name></boy>";
//        把 xml 文件 unmarshal 成 java object
        Boy boy2 = (Boy) unmarshaller.unmarshal(new StringReader(xml));
        System.out.println(boy2.name);
    }


}
