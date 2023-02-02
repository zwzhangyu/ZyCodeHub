package com.lagou.rmpoints.service;

import com.lagou.rmpoints.entity.Points;
import com.lagou.rmpoints.repository.PointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PointsService {
    @Autowired
    private PointsRepository orderRepository;

    @Transactional
    public int addPoints(Integer memberId,Integer points){
        return orderRepository.insert(new Points(memberId,points));
    }
}
