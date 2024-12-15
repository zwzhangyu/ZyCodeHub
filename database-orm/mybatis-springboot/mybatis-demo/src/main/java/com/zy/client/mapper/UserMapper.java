package com.zy.client.mapper;

import com.zy.client.bean.UserBean;

/*******************************************************
 * Created by ZhangYu on 2024/10/20
 * Description :
 * History   :
 *******************************************************/
public interface UserMapper {

    UserBean selectById(UserBean userBean);
}
