package com.tangcheng.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tangcheng.app.business.config.RestTemplateConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: tangcheng
 * @description:
 * @since: Created in 2018/09/30 14:45
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RestTemplateConfig.class)
@Slf4j
public class DownLoadWxPicTest {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * {
     * "item": [
     * {
     * "media_id": "6ijw4_-RotS_ChUsdkPM8pewCGRPo2udeb6T4LV0nb0",
     * "name": "CropImage",
     * "update_time": 1538105998,
     * "url": "http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/OQcmKXAIJV1tQmwR3Lc9ePDup5byH5KVT7TMH5KjVY2Z70Ztmy4c0lYdVoasbvQl4Au4Wqfa5LXdKia8aFj9jug\/0?wx_fmt=jpeg"
     * }
     * ],
     * "total_count": 12,
     * "item_count": 13
     * }
     * <p>
     * https://www.cnblogs.com/softidea/p/9438783.html
     */
    @Test
    public void download() {
        String tmpDir = System.getProperty("java.io.tmpdir");
        File dir = new File(tmpDir, "media");

        String batchGetUrl = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token={accessToken}";
        Map<String, String> request = new HashMap<>();
        request.put("offset", "20");
        request.put("count", "20");
        request.put("type", "image");
        String accessToken = "14_rcAT5z3Kz7iYDhv0NQrCV5zxUH12dZB0azjLnRAdM1xGVnfV73hVc9oDdkMd1wJMMt9pQRrLYyK8mdXEpqBs5PoO90C_-LJyRDNYR-H6SqwsOsCdRivpj1ifWzuJLsOTUyTXHggNQNHAJRs4UCEfAFAIQS";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(batchGetUrl, request, String.class, accessToken);
        String body = responseEntity.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        String total_count = jsonObject.getString("total_count");
        String item_count = jsonObject.getString("item_count");
        log.info("total_count:{} , item_count:{}", total_count, item_count);
        JSONArray item = jsonObject.getJSONArray("item");
        for (int i = 0; i < item.size(); i++) {
            JSONObject material = item.getJSONObject(i);
            String url = material.getString("url");
            String media_id = material.getString("media_id");
            String name = material.getString("name");
            ResponseEntity<byte[]> oneImageFile = restTemplate.getForEntity(url, byte[].class);
            try (ByteArrayInputStream source = new ByteArrayInputStream(oneImageFile.getBody())) {
                File destination = new File(dir, String.join("-", String.valueOf(i), media_id, name, ".jpg"));
                FileUtils.copyInputStreamToFile(source, destination);
                log.info(destination.getAbsolutePath());
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
            }
        }
        log.info("tmpDir:{}", dir.getAbsolutePath());
    }

}
