package com.example.demo1;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


/**
 * @Author baiyu
 * @Date 2023/4/6 21:13
 * @Description
 */

@RestController
@Slf4j
public class PromthusController {

    @DubboReference
    IOrderService orderService;

    @Autowired
    private PrometheusMeterRegistry registry;

    public static Counter curlFailCount;

    public static Histogram curlTime;
    @Value("${spring.application.name}")
    private String applicationName;

    @PostConstruct
    public void init() {
        curlFailCount = Counter.build().name("curl_fail_count").help("curl失败数").labelNames("application", "modules").register(registry.getPrometheusRegistry());
        curlTime = Histogram.build().name("CurlTime").help("curl请求时间").labelNames("application", "modules").register(registry.getPrometheusRegistry());
    }


    @RequestMapping(value = "/gateway1")
    public String gateway1() {
        String result = "";
        try {
            result = orderService.getOrderInfo();
        } catch (Exception e) {
            //do nothing
        } finally {
            log.info("-------------");
            curlTime.labels(applicationName, applicationName).startTimer();
            curlFailCount.labels(applicationName, applicationName).inc();
        }
        return result;
    }

}