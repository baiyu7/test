package com.example.demo1;

/**
 * @Author baiyu
 * @Date 2023/3/16 15:00
 * @Description
 */

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 提供方实现接口并暴露服务
 */
@DubboService
public class IOrderServiceImpl implements IOrderService {
    /**
     * 发送订单信息
     * @return
     */
    @Override
    public String getOrderInfo() {


        return "getOrderInfo okok";
    }
}
