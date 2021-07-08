package xyz.kit00.blog.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;

@RestController
public class ImageController {
    @ResponseBody
    @RequestMapping("/uploadImg")
    public JSONObject upload(@RequestPart("file") MultipartFile cover)throws IOException {
        JSONObject result=new JSONObject();
        if(!cover.isEmpty()){
            //保存到文件服务器，OSS服务器
            String originalFilename = cover.getOriginalFilename();
            File img = new File("F:\\project\\blog\\src\\main\\resources\\static\\img\\" + originalFilename);
            cover.transferTo(img);
            result.put("location","img/"+originalFilename);
        }
        return result;
    }
}
