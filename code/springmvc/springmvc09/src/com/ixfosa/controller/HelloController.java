package com.ixfosa.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ixfosa on 2021/4/11 16:00
 */

@Controller
public class HelloController {

    @RequestMapping("demo1")
    public void demo1(String fileName, HttpServletRequest req, HttpServletResponse res) throws IOException {
        // 设置响应流中文件进行下载
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        // 把二进制流放入到响应体中.
        ServletOutputStream outputStream = res.getOutputStream();
        String path = req.getServletContext().getRealPath("files");
        System.out.println(path);

        File file = new File(path, fileName);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    // 显示要下载的文件
    @RequestMapping("show")
    public String show(HttpServletRequest req, Model model) {
        String realPath = req.getServletContext().getRealPath("files");
        System.out.println("realpath:" + realPath);
        File dir = new File(realPath);
        File[] files = dir.listFiles();
        ArrayList<String> fileName = new ArrayList<>();
        for (File file : files) {
            fileName.add(file.getName());
        }
        System.out.println();
        model.addAttribute("files", fileName);
        return "show.jsp";
    }


    @RequestMapping("download")
    public void download(String fileName, HttpServletRequest req, HttpServletResponse res) throws IOException {
        System.out.println(fileName);
        // 设置下载文件使用的报头
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        String realPath = req.getServletContext().getRealPath("files");

        // 读入文件
        FileInputStream in = new FileInputStream(realPath + "//" + fileName);

        // 得到响应对象的输出流，用于向客户端输出二进制数据
        ServletOutputStream out = res.getOutputStream();
        int len = 0;
        byte[] buf = new byte[1024];

        while ((len = in.read(buf)) != -1) {
            out.write(buf, 0, len);
        }

        out.flush();
        in.close();
        out.close();
    }
}
