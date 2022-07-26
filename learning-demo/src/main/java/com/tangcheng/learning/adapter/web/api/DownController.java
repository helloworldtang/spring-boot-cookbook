/**
 * @Auther: cheng.tang
 * @Date: 2019/2/11
 * @Description:
 */
package com.tangcheng.learning.adapter.web.api;

import com.tangcheng.learning.adapter.web.dto.req.DownUrlReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @Auther: cheng.tang
 * @Date: 2019/2/11
 * @Description:
 */
@Api(tags = "下载相关", description = "使用RestTemplate下载")
@RestController
@RequestMapping("download")
@Slf4j
public class DownController {

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation("根据URL下载文件")
    @PostMapping("down")
    public ResponseEntity<String> downFromUrl(@RequestBody @Validated DownUrlReq downUrlReq) {
//        https://www.cnblogs.com/softidea/p/9438783.html
        ResponseEntity<File> responseEntity = restTemplate.execute(downUrlReq.getUrl(), HttpMethod.GET, null, response -> {
            File downFile = File.createTempFile("download", ".png");
            log.info("downFile:{}", downFile.getPath());
            InputStream body = response.getBody();
            FileCopyUtils.copy(body, new FileOutputStream(downFile));
            return ResponseEntity.status(response.getStatusCode()).headers(response.getHeaders()).body(downFile);
        });
        return ResponseEntity.ok(responseEntity.getStatusCode().toString());
    }


}
