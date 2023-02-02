package com.lagou.tmmall.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("rm-order")
public interface OrderFeignClient {
    @GetMapping("/create_order")
    public String createOrder(@RequestParam("orderId") Integer orderId,
                              @RequestParam("memberId") Integer memberId,
                              @RequestParam("goodsId") Integer goodsId,
                              @RequestParam("points") Integer points,
                              @RequestParam("quantity") Integer quantity
    );
}