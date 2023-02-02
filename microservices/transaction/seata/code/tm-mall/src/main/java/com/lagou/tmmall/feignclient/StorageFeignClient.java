package com.lagou.tmmall.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("rm-storage")
public interface StorageFeignClient {
    @GetMapping("/reduce_storage")
    public String reduceStorage(@RequestParam("goodsId") Integer goodsId, @RequestParam("quantity") Integer quantity);
}