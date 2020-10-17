package com.gg.controller;

import com.gg.domain.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
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
    @RequestMapping("/testUpload2")
    public String fileuoload3(MultipartFile upload) throws Exception {
        System.out.println("跨服务器文件上传...");

        // 定义上传文件服务器路径
        String path = "http://localhost:9090/upload/";

        // 说明上传文件项
        // 获取上传文件的名称
        String filename = upload.getOriginalFilename();
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;

        // 创建客户端的对象
        Client client = Client.create();

        // 和图片服务器进行连接
        WebResource webResource = client.resource(path + filename);

        // 上传文件
        webResource.put(upload.getBytes());

        return "success";
    }
}
