package com.ixfosa.controller;


import com.ixfosa.pojo.FileDomain;
import com.ixfosa.pojo.MultiFileDomain;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
public class DemoController {

    @RequestMapping("demo1")
    public String demo1(MultipartFile file, String description) throws IOException {
        System.out.println(description);
        String filename = file.getOriginalFilename();

        String suffix = filename.substring(filename.lastIndexOf("."));

        // 判断上传文件类型
        if (suffix.equalsIgnoreCase(".png")) {
            UUID uuid = UUID.randomUUID();

            FileUtils.copyInputStreamToFile(file.getInputStream(),
                    new File("E:/" + uuid + suffix));

            return "show.jsp";
        } else {
            return "error.jsp";
        }
    }

    @RequestMapping("demo2")
    public String demo2(FileDomain fileDomain, HttpServletRequest request) {
        String realpath = request.getServletContext()
                .getRealPath("uploadfiles");

        System.out.println(realpath);
        String fileName = fileDomain.getFile().getOriginalFilename();
        File targetFile = new File(realpath, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        // 上传
        try {
            fileDomain.getFile().transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "show.jsp";
    }

    @RequestMapping("demo3")
    public String demo3(MultiFileDomain multiFileDomain, HttpServletRequest request) {
        String realpath = request.getServletContext().getRealPath("uploadfiles");

        File targetDir = new File(realpath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        List<MultipartFile> fileList = multiFileDomain.getMultipartFileList();
        for (MultipartFile multipartFile : fileList) {
            String fileName = multipartFile.getOriginalFilename();
            File targetFile = new File(realpath, fileName);

            try {
                multipartFile.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "showMulti.jsp";
    }
}
