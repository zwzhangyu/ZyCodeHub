package com.lagou.tmmall.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("rm-points")
public interface PointsFeignClient {
    @GetMapping("/add_points")
    public String addPoints(@RequestParam("memberId") Integer memberId, @RequestParam("points") Integer points);
}