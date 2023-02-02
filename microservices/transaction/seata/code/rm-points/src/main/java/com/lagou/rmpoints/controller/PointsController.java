package com.lagou.rmpoints.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagou.rmpoints.entity.Points;
import com.lagou.rmpoints.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PointsController {
    @Autowired
    private PointsService pointsService;
    @GetMapping("/add_points")
    public String addPoints(Integer memberId,Integer points) throws JsonProcessingException {
        Map result = new HashMap<>();
        int entity = pointsService.addPoints(memberId, points);
        result.put("code", "0");
        result.put("message", "add points success");
        return new ObjectMapper().writeValueAsString(result);
    }
}
