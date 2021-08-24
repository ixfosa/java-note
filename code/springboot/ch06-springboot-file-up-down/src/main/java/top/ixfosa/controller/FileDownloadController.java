package top.ixfosa.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by ixfosa on 2021/5/31 15:51
 */
@Controller
public class FileDownloadController {

    @RequestMapping("download")
    @ResponseBody
    public String fileDownload(String fileName, HttpServletResponse res) throws IOException {
        // 设置响应流中文件进行下载
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        FileInputStream in = new FileInputStream(new File("E:/img/" + fileName));
        ServletOutputStream out = res.getOutputStream();

        byte[] buf = new byte[2048];
        int len;
        while (-1 != (len = in.read(buf))) {
            out.write(buf, 0, len);
        }
        out.flush();
        in.close();
        out.close();

        return "ok";
    }
}
