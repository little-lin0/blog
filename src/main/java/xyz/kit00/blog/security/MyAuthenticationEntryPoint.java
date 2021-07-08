package xyz.kit00.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
@Service
public class MyAuthenticationEntryPoint  extends LoginUrlAuthenticationEntryPoint {
    public MyAuthenticationEntryPoint (@Value("/doLogin") String loginFormUrl) {
        super(loginFormUrl);
    }




    @Override
    protected String determineUrlToUseForThisRequest(
            HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) {
            
        return super.determineUrlToUseForThisRequest(request, response,
                exception);
    }



}
