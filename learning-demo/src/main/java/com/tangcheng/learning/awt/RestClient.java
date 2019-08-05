/**
 * @Auther: cheng.tang
 * @Date: 2019/2/2
 * @Description:
 */
package com.tangcheng.learning.awt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Auther: cheng.tang
 * @Date: 2019/2/2
 * @Description:
 */
@Slf4j
@Service
public class RestClient {

    public static final String DOT = ".";

    @Autowired
    private RestTemplate restTemplate;

    public File downFileToLocal(String url, String suffix) throws IOException {
        String actualSuffix;
        if (StringUtils.isNotBlank(suffix)) {
            actualSuffix = suffix.startsWith(".") ? suffix : DOT + suffix;
        } else {
            String extension = FilenameUtils.getExtension(url);
            if (StringUtils.isNotBlank(extension)) {
                actualSuffix = DOT + extension;
            } else {
                actualSuffix = "";
            }
        }
        ResponseExtractor<ResponseEntity<File>> responseExtractor = new ResponseExtractor<ResponseEntity<File>>() {
            @Override
            public ResponseEntity<File> extractData(ClientHttpResponse response) throws IOException {
                File downFile = File.createTempFile("download", actualSuffix);
                log.info("down {} to local:{}", url, downFile.getPath());
                FileCopyUtils.copy(response.getBody(), new FileOutputStream(downFile));
                return ResponseEntity.status(response.getStatusCode()).headers(response.getHeaders()).body(downFile);
            }
        };
        ResponseEntity<File> responseEntity = restTemplate.execute(url, HttpMethod.GET, null, responseExtractor);
        if (responseEntity != null) {
            return responseEntity.getBody();
        }
        log.error("fail to downFileToLocal {} ", url);
        throw new IOException("fail to downFileToLocal");
    }

    public File downFileToLocal(String url) throws IOException {
        return downFileToLocal(url, null);
    }

}
