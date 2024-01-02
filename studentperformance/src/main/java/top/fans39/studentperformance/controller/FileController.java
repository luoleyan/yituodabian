package top.fans39.studentperformance.controller;

import cn.hutool.core.io.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.fans39.studentperformance.common.Result;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

// 文件上传/下载接口
@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${ip}")
    private String ip;

    @Value("${server.port}")
    private String port;
    private static final String ROOT_PATH = System.getProperty("user.dir") + "/files";

    // 文件上传
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        long flag = System.currentTimeMillis();
        String fileName = flag + "_" + originalFilename;

        File finalFile = new File(ROOT_PATH + "/" + fileName);
        if (!finalFile.getParentFile().exists()) {
            finalFile.getParentFile().mkdirs();
        }
        file.transferTo(finalFile);

        String url = "http://" + ip + ":" + port + "/files/download?fileName=" + fileName;
        return Result.success(url);
    }

    // 文件下载
    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        File file = new File(ROOT_PATH + "/" + fileName);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setContentType("application/octet-stream");
        FileUtil.writeToStream(file, servletOutputStream);
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
