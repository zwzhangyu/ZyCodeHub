package com.lagou.rmstorage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagou.rmstorage.entity.Storage;
import com.lagou.rmstorage.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StorageController {
    @Resource
    private StorageService storageService;
    @GetMapping("/reduce_storage")
    public String reduceStorage(Integer goodsId,Integer quantity) throws JsonProcessingException {
        Map result = new HashMap<>();
        int storage = storageService.reduceStorage(goodsId, quantity);
        result.put("code", "0");
        result.put("message", "reduce storage success");
        return new ObjectMapper().writeValueAsString(result);
    }
}
