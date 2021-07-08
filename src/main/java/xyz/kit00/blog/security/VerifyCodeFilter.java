package xyz.kit00.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class VerifyCodeFilter extends AbstractAuthenticationProcessingFilter {
    @Value("/doLogin")
    private String defaultFilterProcessUrl;

    protected VerifyCodeFilter(@Value("/doLogin")String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }
    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
            // 验证码验证
            String requestCaptcha = request.getParameter("captcha");
            String genCaptcha = (String) request.getSession().getAttribute("verifyCode");
            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
//                   throw new CaptchaException("验证码错误!");
                Map<String, Object> map = new HashMap<>();
                map.put("msg", "验证码错误");
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                // 对象转json传输给前端
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
            }
//            else
//                chain.doFilter(request, response);


        }
        chain.doFilter(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
         return  null;
    }
}