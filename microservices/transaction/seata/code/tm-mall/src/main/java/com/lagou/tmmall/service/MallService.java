package com.lagou.tmmall.service;

import com.lagou.tmmall.feignclient.OrderFeignClient;
import com.lagou.tmmall.feignclient.PointsFeignClient;
import com.lagou.tmmall.feignclient.StorageFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MallService {
    @Resource
    OrderFeignClient orderFeignClient;
    @Resource
    PointsFeignClient pointsFeignClient;
    @Resource
    StorageFeignClient storageFeignClient;

    @GlobalTransactional(name = "seata-group-tx-mall", rollbackFor = {Exception.class})
    public String sale(Integer orderId,Integer memberId,Integer goodsId,Integer points,Integer quantity) {
        String orderResult = orderFeignClient.createOrder(orderId,memberId,goodsId,points,quantity);
        String pointsResult = pointsFeignClient.addPoints(memberId, points);
        String storageResult = storageFeignClient.reduceStorage(goodsId, quantity);
        return orderResult + " / " + pointsResult + " / " + storageResult;
    }

}
