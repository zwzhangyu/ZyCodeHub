package com.zy.client.mapper;

import com.zy.client.bean.Order;
import com.zy.client.bean.UserOrderRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*******************************************************
 * Created by ZhangYu on 2024/12/15
 * Description :
 * History   :
 *******************************************************/
@Mapper
public interface OrderMapper {

    List<Order> selectOrders(UserOrderRequest request);

}
