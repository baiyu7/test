package com.example.demo1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import org.apache.logging.log4j.spi.CopyOnWrite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Author baiyu
 * @Date 2023/6/15 17:19
 * @Description
 */
@RestController
@WebServlet(name = "prometheusServlet", urlPatterns = "")
public class Prometheus extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(Prometheus.class.getName());

    public static CollectorRegistry registry = CollectorRegistry.defaultRegistry;

    public static Map<Object, Method> invoke = Maps.newHashMap();

    @Autowired
    protected PrometheusMeterRegistry prometheusMeterRegistry;


    @Override
    @RequestMapping("/metrics")
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(TextFormat.CONTENT_TYPE_004);
        Writer writer = null;
        try {
            writer = response.getWriter();

            // 从请求头中获取Authorization字段，并解码获取用户名和密码
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Basic ")) {
                String credentials = new String(Base64.getDecoder().decode(authHeader.substring("Basic ".length())));
                String[] parts = credentials.split(":", 2);
                String username = parts[0];
                String password = parts[1];

            }
            prometheusMeterRegistry.scrape(writer);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
