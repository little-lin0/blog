package xyz.kit00.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        String msg=null;
        if(exception.getMessage().equals("Bad credentials"))
            msg ="密码错误";
        else
           msg=exception.getMessage();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 对象转json传输给前端
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();

    }



}
