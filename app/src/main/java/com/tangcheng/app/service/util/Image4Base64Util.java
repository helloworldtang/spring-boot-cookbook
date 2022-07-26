package com.tangcheng.app.service.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.Base64;

@Component
public class Image4Base64Util {


    //    @Autowired
//    private Producer producer;
//
    public String image2Base64() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        String code = lineCaptcha.getCode();
        lineCaptcha.write(output);
//        ImageIO.write(lineCaptchaImage, "PNG", output);
        return DatatypeConverter.printBase64Binary(output.toByteArray());
    }


    public void base64Image2PNG(String base64Image, File outFile) throws IOException {
        try (FileOutputStream imageOutFile = new FileOutputStream(outFile)) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }


    public String base64Image2PNG(String base64Image) throws IOException {
        File outFile = new File("1.png");
        base64Image2PNG(base64Image, outFile);
        return outFile.getAbsolutePath();
    }


}
