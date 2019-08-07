package com.tangcheng.app.api.web;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by tangcheng on 8/16/2017.
 */
@Controller
@RequestMapping("profile/upload")
public class PictureUploadController {

    public static final Resource PICTURE_DIR = new FileSystemResource("./picture");

    @GetMapping
    public String uploadPage() {
        return "profile/uploadPage";
    }

    @PostMapping
    public String onUpload(MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();
        File tempFile = File.createTempFile("pic", filename.substring(filename.lastIndexOf(".")), PICTURE_DIR.getFile());
        try (InputStream inputStream = multipartFile.getInputStream()) {
            OutputStream outputStream = new FileOutputStream(tempFile);
            IOUtils.copy(inputStream, outputStream);
        }
        return "profile/uploadPage";
    }


}
