package com.competition.firemon.controller;

import com.competition.firemon.bean.Msg;
import com.competition.firemon.config.video.NonStaticResourceHttpRequestHandler;
import com.competition.firemon.utils.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class FileRestController {

    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    @Autowired
    RedisUtil redisUtil;

    //前端获取视频文件
    @GetMapping("/video")
    public Msg videoPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //假如我把视频1.mp4放在了static下的video文件夹里面
        //sourcePath 是获取resources文件夹的绝对地址
        //realPath 即是视频所在的磁盘地址
//        String sourcePath = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
//        String realPath = sourcePath + "static/video/1565245273111989c0fdbab308.mp4_last.mp4";

        String realPath = (String) redisUtil.get("video");
        if(StringUtils.isEmpty(realPath)){
            String sourcePath = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
            realPath = sourcePath + "static/video/1565245273111989c0fdbab308.mp4_last.mp4";
        }

        log.info("访问的路径视频"+realPath);
        Path filePath = Paths.get(realPath);
        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
            return Msg.success();
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            return  Msg.fail().add("error","找不到视频资源");
        }
    }


    //嵌入式上传视频文件
    @PostMapping("/upload")
    public Msg upload(MultipartFile uploadFile, HttpServletRequest request) {
        /*定义文件的存储路径,如下，是在linux和mac上定义的文件路径
        /private/var/folders/8x/4zvnbqmj1w33cqmzrpygzbth0000gn/T/tomcat-docbase.5206733816001100271.8080/uploadFile
        */

        String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        File dir = new File(realPath);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            dir.mkdirs();
        }

        try {
            String filename = uploadFile.getOriginalFilename();
            //服务端保存的文件对象
            File fileServer = new File(dir, filename);
            log.info("file文件真实路径:" + fileServer.getAbsolutePath());
            //2，实现上传
            uploadFile.transferTo(fileServer);
            String filePath = request.getScheme() + "://" +
                    request.getServerName() + ":"
                    + request.getServerPort()
                    + "/uploadFile/" + filename;

            redisUtil.set("video",fileServer.getAbsolutePath());
            //3，返回可供访问的网络路径
            return Msg.success().add("path",filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Msg.fail().add("error","上传失败");
    }

}
