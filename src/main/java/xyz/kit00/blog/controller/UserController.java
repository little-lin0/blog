package xyz.kit00.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import xyz.kit00.blog.content.Sample;
import xyz.kit00.blog.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/searchUser")
    public JSONObject searchUser (@RequestParam Map<String,String> map){
        return userService.searchUserForList(map);
    }
    @RequestMapping("/userList")
    public JSONObject searchUser (@RequestParam("pageNum") Integer pageNum,@RequestParam("pageSize") Integer pageSize){

        return userService.searchAll(pageNum,pageSize);
    }
    @RequestMapping(value = "/addUser",method = RequestMethod.PUT)
    public JSONObject addUser(@RequestParam Map<String,String> map, HttpServletRequest request, HttpServletResponse response){

            String verifyCode=String.valueOf(request.getSession().getAttribute("verifyCode"));
            String captcha=map.get("captcha");
            if(verifyCode.equals(captcha)){
                if(Sample.checkText(map.values().toString()))
                return userService.insertUser(map);
                else {
                    JSONObject result=new JSONObject();
                    result.put("msg","提交内容违规");
                    result.put("code","500");
                    return result;
                }
            }
             else {
                 JSONObject result=new JSONObject();
                 result.put("msg","验证码错误");
                 return result;
             }
    }

     @RequestMapping("/searchByCondition")
    public  JSONObject searchByCondition(@RequestParam Map<String,String> map){
        return userService.searchByCondition(map);
     }
     @RequestMapping("/searchUserInfo")
    public  JSONObject searchUserInfo(@RequestParam Map<String,String> map){
        return userService.searchUserInfo(map);
     }
}
