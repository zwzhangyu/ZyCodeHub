package com.lagou.rmorder.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lagou.rmorder.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseMapper<Order> {

}
