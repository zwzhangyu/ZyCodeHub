package com.zy.client.service;

import com.zy.client.bean.Order;
import com.zy.client.bean.UserOrderRequest;
import com.zy.client.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*******************************************************
 * Created by ZhangYu on 2024/12/15
 * Description :
 * History   :
 *******************************************************/
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public List<Order> getOrders(UserOrderRequest userOrders) {
        return orderMapper.selectOrders(userOrders);
    }
}
