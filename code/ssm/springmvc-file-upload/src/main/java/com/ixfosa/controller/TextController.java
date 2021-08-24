package com.ixfosa.controller;

import com.ixfosa.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
public class TextController {

    @RequestMapping("text1")
    @ResponseBody
    public User doText(User user, HttpServletRequest request) throws IOException {
        System.out.println("doText...");
        ServletInputStream is = request.getInputStream();
        byte[] buf = new byte[1024];
        int len = 0;

        while ((len = is.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len, "utf-8"));
        }
        return user;
    }

    @RequestMapping("text2")
    @ResponseBody
    public User doText(HttpServletRequest request) throws IOException {
        ServletInputStream is = request.getInputStream();

        byte[] buf = new byte[1024];
        int len = 0;

        while ((len = is.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len, "utf-8"));
        }

        return null;

        // ------WebKitFormBoundaryeHfAzQ5xwPE4SWGk
        // Content-Disposition: form-data; name="name"
        //
        // 夏
        // ------WebKitFormBoundaryeHfAzQ5xwPE4SWGk
        // Content-Disposition: form-data; name="password"
        //
        // 11
        // ------WebKitFormBoundaryeHfAzQ5xwPE4SWGk--
    }

    @RequestMapping("file")
    @ResponseBody
    public String doFile(MultipartFile file, HttpServletRequest request) throws IOException {
       return "";
    }
}
