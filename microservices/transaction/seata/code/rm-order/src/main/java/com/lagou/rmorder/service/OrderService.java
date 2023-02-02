package com.lagou.rmorder.service;

import com.lagou.rmorder.entity.Order;
import com.lagou.rmorder.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderService {
    @Resource
    private OrderRepository orderRepository;

    @Transactional
    public int createOrder(Integer orderId,Integer memberId,Integer goodsId,Integer points,Integer quantity){
        return orderRepository.insert(new Order( memberId,goodsId,points,quantity));
    }
}
