package com.ixfosa.controller;


import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    @RequestMapping("file1")
    @ResponseBody
    public String fileupload1(List<MultipartFile> files) throws IOException {
        return getString(files);
    }

    @RequestMapping("file2")
    @ResponseBody
    public String fileupload2(List<MultipartFile> files) throws IOException {
        return getString(files);
    }

    private String getString(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("E:/" + uuid + suffix));
        }
        return "file1...";
    }

    @RequestMapping("file3")
    @ResponseBody
    public List<String> showFiles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应流中文件进行下载
        // /springmvc_file_upload_war_exploded
        // System.out.println(request.getServletContext().getContextPath());
        String path = request.getServletContext().getRealPath("files");
        System.out.println(path);
        File dir = new File(path);
        System.out.println(dir);
        File[] files = dir.listFiles();

        // 获取该目录下的所有文件名
        ArrayList<String> fileURLList = new ArrayList<>();
        for (File file : files) {
            fileURLList.add("download?fileName=" + file.getName());
        }
        return fileURLList;
    }

    @RequestMapping("download")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        String path = request.getServletContext().getRealPath("files");

        // 读入文件
        FileInputStream in = new FileInputStream(path + "/" + fileName);

        ServletOutputStream out = response.getOutputStream();

        byte[] buf = new byte[1024];
        int len = 0;

        while ((len = in.read(buf)) != -1 && in != null) {
            out.write(buf, 0, len);
        }
        out.flush();
        in.close();
        out.close();
    }
}
