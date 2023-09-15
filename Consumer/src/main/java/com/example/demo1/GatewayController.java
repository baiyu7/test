package com.example.demo1;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * @Author baiyu
 * @Date 2023/3/16 21:49
 * @Description
 */
@RestController
@Slf4j
public class GatewayController {

    @DubboReference
    IOrderService orderService;

    @Autowired
    private MeterRegistry registry;

    private Counter counter;


    @PostConstruct
    private void init() {
        counter = registry.counter("requests_total", "status", "success");
    }

    @RequestMapping(value = "/gateway")
    public String gateway() {
        String result = "";
        try {
            result = orderService.getOrderInfo();
        } catch (Exception e) {
            //do nothing
        } finally {
            counter.increment();
            log.info("-------------");

        }
        return result;
    }

}