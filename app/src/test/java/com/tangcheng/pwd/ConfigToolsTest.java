package com.tangcheng.pwd;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Auther: cheng.tang
 * @Date: 2019/6/13
 * @Description:
 */
public class ConfigToolsTest {

    @Test
    public void decryptTest() throws Exception {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAL4NMFgT2wEzYKUqLhGN4JyhwBvcJXHSD+lgDxFUWNRD+O+Ej6Et93K0PAhC4HqvWM7H4BJqy6J/gIWzFEoQ9RcCAwEAAQ==";
        String password = "T5Z2dzBYyW8Qe2yooI/L0yB6Plq+Zo8Bzto9j6SllWw8dZsTh3eeQ7Nb31L9D1qqViu5Bxo3rOHGhYUFP+d+kg==";
        String decrypt = ConfigTools.decrypt(publicKey, password);
        assertThat(decrypt).isEqualTo("password_dddddd2Uvp42tN");
    }

    @Test
    public void generate() throws Exception {
        String password = "password_dddddd2Uvp42tN";
        String[] arr = ConfigTools.genKeyPair(512);
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        System.out.println("password:" + ConfigTools.encrypt(arr[0], password));
    }


}