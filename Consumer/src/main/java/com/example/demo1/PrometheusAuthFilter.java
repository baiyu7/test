package com.example.demo1;

import org.springframework.context.annotation.Configuration;

/**
 * @Author baiyu
 * @Date 2023/6/15 18:28
 * @Description
 */
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class PrometheusAuthFilter implements Filter {
    private static final String AUTH_USERNAME = "your_username";
    private static final String AUTH_PASSWORD = "your_password";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化方法，可以在这里进行一些初始化操作
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 从请求头中获取Authorization字段，并解码获取用户名和密码
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            String credentials = new String(Base64.getDecoder().decode(authHeader.substring("Basic ".length())));
            String[] parts = credentials.split(":", 2);
            String username = parts[0];
            String password = parts[1];

            // 验证用户名和密码是否匹配
            if (username.equals(AUTH_USERNAME) && password.equals(AUTH_PASSWORD)) {
                // 鉴权成功，继续处理请求
                chain.doFilter(request, response);
                return;
            }
        }

        // 鉴权失败，返回401 Unauthorized响应
        httpResponse.setHeader("WWW-Authenticate", "Basic realm=\"Prometheus Metrics\"");
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public void destroy() {
        // 销毁方法，可以在这里进行一些清理操作
    }
}