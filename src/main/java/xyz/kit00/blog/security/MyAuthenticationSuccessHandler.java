package xyz.kit00.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Service;
import xyz.kit00.blog.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
   @Autowired
   private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
//        登录信息放入cookie中
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = user.getUsername();
        Map<String, String> usermap = new HashMap<>();
        usermap.put("username",name);
        xyz.kit00.blog.bean.User user1=userService.searchUserForOne(usermap);
        Cookie userame=new Cookie("username",name);
        userame.setPath("/");
        response.addCookie(userame);
        Cookie userId=new Cookie("userId",user1.getId().toString());
        userId.setPath("/");
        response.addCookie(userId);
        Cookie head_img=new Cookie("head_img",user1.getHead_img());
        head_img.setPath("/");
        response.addCookie(head_img);
        request.getSession().setAttribute("userId",user1.getId());
        request.getSession().setAttribute("username",name);
        DefaultSavedRequest savedRequest=(DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        System.out.println(   "来自:"+request.getServletContext().getAttribute("Referer"));
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "登录成功！");
        map.put("principal", authentication.getPrincipal());
        map.put("goto",savedRequest!=null?savedRequest.getRequestURL():"index.html");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 对象转json传输给前端
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();


    }




}
