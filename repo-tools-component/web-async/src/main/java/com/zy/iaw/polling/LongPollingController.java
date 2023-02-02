package com.zy.iaw.polling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/polling")
@Slf4j
public class LongPollingController {

    @RequestMapping("/test1")
    public void test1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        // 释放http连接，转为异步
        AsyncContext context = request.startAsync();
        // 4秒才超时了，超时也会中断当前请求直接返回
        context.setTimeout(30000L);
        // 异步处理，等待3秒后执行
        executorService.schedule(() -> {
            PrintWriter writer = null;
            try {
                writer = context.getResponse().getWriter();
                writer.print("hello world");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                context.complete();
            }
        }, 10, TimeUnit.SECONDS);
    }

    @GetMapping("/async-deferredresult")
    public DeferredResult<ResponseEntity<?>> handleReqDefResult() {
        log.info("Received async-deferredresult request");
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();
        ForkJoinPool.commonPool().submit(() -> {
            log.info("Processing in separate thread");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
            }
            output.setResult(ResponseEntity.ok("ok"));
        });
        log.info("servlet thread freed");
        return output;
    }
}




