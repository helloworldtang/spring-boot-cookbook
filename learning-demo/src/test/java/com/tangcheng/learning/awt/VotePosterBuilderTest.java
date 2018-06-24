package com.tangcheng.learning.awt;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * spring-boot-cookbook
 *
 * @author tangcheng
 * @date 6/25/2018 12:04 AM
 */
public class VotePosterBuilderTest {

    @Test
    public void build() throws IOException {
        String templateUrl = "https://n1image.hjfile.cn/mh/2018/06/11/a658a1f29f1c94f377f69f91fcfa2d89.jpg";
        String qrCodeUrl = "https://n1image.hjfile.cn/mh/2018/06/08/0b4478fb470989ff979554b21b89af1f.jpg";
        String heaImgUrl = "http://thirdwx.qlogo.cn/mmopen/vi_32/lJgd2Og43uNMhDWY9DAQiaJicGabC03O4YauOCF4PaWoMEdicZHpUaqN4icZ5ajbIlFVWicM3d4bCyCuPwWTaUbIqSQ/132";
        heaImgUrl = "https://wx.qlogo.cn/mmopen/vi_32/xqGjU48C4NK1ibFYMXIwfnzymd1M2YhS39UrQRtIxabV8yQ85LD9giaRp0cRR7U8bqohk2qBIrhjz80lgTlwLbnw/0";
        String nickName = "呢称";
        new VotePosterBuilder(templateUrl).addNickname(nickName).addQrcode(qrCodeUrl).addHeadImage(heaImgUrl).build();
    }
}