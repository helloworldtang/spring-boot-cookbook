package com.tangcheng.app.service.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Image4Base64UtilTest {

    @Autowired
    private Image4Base64Util image4Base64Util;

    @Test
    public void base64Image2PNG() throws Exception {
        String base64Image = image4Base64Util.image2Base64();
        String filePath = image4Base64Util.base64Image2PNG(base64Image);
        System.out.println(filePath);
    }


}