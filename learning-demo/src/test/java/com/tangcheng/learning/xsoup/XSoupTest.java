package com.tangcheng.learning.xsoup;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import us.codecraft.xsoup.Xsoup;

import java.util.List;

public class XSoupTest {

//    https://github.com/code4craft/xsoup

    @Test
    public void testSelect() {

        String html =
                "<html>" +
                        "<div><a href='https://github.com'>github.com</a></div>" +
                        "<table>" +
                        "<tr>" +
                        "<td>line-1-col-1</td><td>line-1-col-2</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td>line-2-col-1</td><td>line-2-col-2</td>" +
                        "</tr>" +
                        "</table>" +
                        "</html>";

        Document document = Jsoup.parse(html);

        String result = Xsoup.compile("//a/@href").evaluate(document).get();
        Assert.assertEquals("https://github.com", result);

        List<String> list = Xsoup.compile("//tr/td/text()").evaluate(document).list();
        Assert.assertEquals("line-1-col-1", list.get(0));
        Assert.assertEquals("line-1-col-2", list.get(1));

        List<String> evaluate = Xsoup.compile("//table/td/text(2)").evaluate(document).list();
        System.out.println(evaluate);


    }

}
