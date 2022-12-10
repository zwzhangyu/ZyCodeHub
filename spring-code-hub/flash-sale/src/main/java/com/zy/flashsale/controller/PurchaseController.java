package com.zy.flashsale.controller;

import com.zy.flashsale.common.Result;
import com.zy.flashsale.service.PurchaseService;
import com.zy.flashsale.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
@Slf4j
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;



    @Autowired
    private TaskService taskService;

    @RequestMapping("/index")
    public ModelAndView purchasePage() {
        return new ModelAndView("purchase");
    }


    @PostMapping("/purchase")
    @ResponseBody
    public Result purchase(Long userId, Long productId, Integer quantity) {
        log.info("抢购商品：userId：{} productId：{} quantity：{} ", userId, productId, quantity);
        boolean success = purchaseService.purchaseByRedis(userId, productId, quantity);
        String message = success ? "抢购成功" : "抢购失败";
        return new Result(success, message);
    }

    /**
     * 手动执行定时任务
     */
    @GetMapping("/startTask")
    @ResponseBody
    public Result startTask() {
        taskService.purchaseTask();
        return new Result();
    }
}