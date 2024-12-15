package com.zy.client.bean;

import lombok.Data;

/*******************************************************
 * Created by ZhangYu on 2024/12/15
 * Description :
 * History   :
 *******************************************************/
@Data
public class Order {
    private Integer id;
    private Integer userId;
    private Integer orderId;
    private String orderDescription;

}
