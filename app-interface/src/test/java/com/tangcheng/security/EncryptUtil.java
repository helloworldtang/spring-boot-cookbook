package com.tangcheng.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncryptUtil {
    /**
     * new StandardPasswordEncoder("secret");情况也一样
     */
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encrypt(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean match(String rawPassword, String password) {
        return encoder.matches(rawPassword, password);
    }

    public static void main(String[] args) {
        String rawPassword = "每次加密结果都不一样，但match时可以验证通过";
        System.out.println("rawPassword:" + rawPassword);
        String result1 = EncryptUtil.encrypt(rawPassword);
        System.out.println(result1 + ".is match:" + EncryptUtil.match(rawPassword, result1));


        String result2 = EncryptUtil.encrypt(rawPassword);
        System.out.println(result2 + ".is match:" + EncryptUtil.match(rawPassword, result1));


        String result3 = EncryptUtil.encrypt(rawPassword);
        System.out.println(result3 + ".is match:" + EncryptUtil.match(rawPassword, result1));


        String result4 = EncryptUtil.encrypt(rawPassword);
        System.out.println(result4 + ",length:" + result4.length() + ".is match:" + EncryptUtil.match(rawPassword, result4));


        String result5 = EncryptUtil.encrypt(rawPassword);
        System.out.println(result5 + ".is match:" + EncryptUtil.match(rawPassword, result5));

        /**
         *
         rawPassword:每次加密结果都不一样，但match时才能验证通过?
         $2a$10$Eo7jEzy67dQA2A86xmUXLe02Qkh63jzX4LTE/xUYBWsNsutzOhtRS.is match:true
         $2a$10$JY179ZzMDoPWdOTBZD7c1eoU2Sp/itq2cDIY2mVVKN1tb.jd3tp7O.is match:true
         $2a$10$pzfHCMgaRk0gIo4mOgSyGuneqJfgv66BZjPoyK1ccCn.GWr0tZy3u.is match:true
         $2a$10$aROp0ID1IAY2Yd3xEmN4CeLegPb7BVfW1x9lX2CkW/tTKgzG/RKXO,length:60.is match:true
         $2a$10$8LPKu0NA8/NJoenlww/7Tu3l.Buq/eT3Ci/kneW79w93eTK98djpu.is match:true
         *
         */

    }
}