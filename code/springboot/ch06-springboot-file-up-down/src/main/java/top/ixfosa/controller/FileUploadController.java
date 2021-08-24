package top.ixfosa.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by ixfosa on 2021/5/31 11:17
 */

@Controller
public class FileUploadController {

    @Value("${file.upload.filepath}")
    private String filepath;

    // 处理单文件上传
    @RequestMapping("upload")
    @ResponseBody
    public String fileUpload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "false";
        }

        String filename = file.getOriginalFilename();
        System.out.println(filename);

        // file.transferTo(new File("E:/" + filename));

        InputStream in = file.getInputStream();
        OutputStream out = new FileOutputStream(new File("E:/" + filename));

        byte[] buf = new byte[4096];
        int len;

        while (-1 != (len = in.read(buf))) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        return "ok!";
    }


    // 处理多文件上传
    @RequestMapping("multfile")
    @ResponseBody
    public String MultFileUpload(List<MultipartFile> files) throws IOException {

        System.out.println(files.size());
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String filename = file.getOriginalFilename();
                File dest = new File(filepath + filename);
                // 判断文件在所在目录是否存在，如果不存在就创建对应的目录。
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdir();
                }
                file.transferTo(dest);
            }
        }
        return "ok!";
    }
}
