package com.gg.controller;

import com.gg.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping("/testUpload")
    public String testUpload(HttpServletRequest request, MultipartFile upload) {
        String fileName = request.getSession().getServletContext().getRealPath("/upload/");
        System.out.println(fileName);
        File file = new File(fileName);
        if (!file.exists()) {
            file.mkdirs();
        }

        String uploadFileName = upload.getOriginalFilename();
        String id = UUID.randomUUID().toString().replace("-", "");
        String saveName = id + "_" + uploadFileName;
        try {
            upload.transferTo(new File(fileName,saveName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("suc upload");
        return "success";
    }
}
