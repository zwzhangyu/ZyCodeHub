package com.zy.client.bean;

import lombok.Data;

import java.util.List;

/*******************************************************
 * Created by ZhangYu on 2024/12/15
 * Description :
 * History   :
 *******************************************************/
@Data
public class UserOrderRequest {
    private List<String>  orderIdList;
    private List<List<String>>  splitOrderIdList;
}
