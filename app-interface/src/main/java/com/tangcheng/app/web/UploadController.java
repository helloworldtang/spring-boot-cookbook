package com.tangcheng.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * spring-boot-cookbook
 *
 * @author : tang.cheng
 * @version : 2017-07-05  17:48
 */
@Controller
@RequestMapping("test/upload")
public class UploadController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("url", "/test/upload");
        return "upload";
    }

    @PostMapping//new annotation since 4.3
    public String upload(@RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttributes) {
        String globalMsg = "globalMsg";
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute(globalMsg, "choose a file to upload");
            return "redirect:/test/upload";
        }
        try {
            byte[] bytes = file.getBytes();
            String filename = file.getOriginalFilename();
            Path path = Paths.get("/tmp/", filename);
            Files.write(path, bytes);
            file.transferTo(Paths.get("/tmp/", "bak_" + filename).toFile());
            String savePath = path.toFile().getAbsolutePath();
            LOGGER.info(savePath);
            redirectAttributes.addFlashAttribute(globalMsg, "success to upload " + filename + ",\n save to " + savePath);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute(globalMsg, e.getMessage());
        }

        return "redirect:/test/upload";
    }

}
