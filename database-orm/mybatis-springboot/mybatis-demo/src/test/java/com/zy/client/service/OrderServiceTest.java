package com.zy.client.service;


import com.google.common.collect.Lists;
import com.zy.client.bean.Order;
import com.zy.client.bean.UserBean;
import com.zy.client.bean.UserOrderRequest;
import com.zy.client.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/*******************************************************
 * Created by ZhangYu on 2024/12/15
 * Description :
 * History   :
 *******************************************************/
@Slf4j
public class OrderServiceTest {

    /**
     * 拆分多条SQL执行大数据量IN查询
     *
     * @throws IOException
     */
    @Test
    public void test_listOrder_batch() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        UserOrderRequest request = new UserOrderRequest();
        List<String> orderIdList = new ArrayList<>();
        for (int i = 0; i < 15000; i++) {
            orderIdList.add(String.valueOf(i));
        }
        // 查询结果
        List<Order> ordersResult = new ArrayList<>();
        // 分批次查询
        List<List<String>> splitOrderIdList = Lists.partition(orderIdList, 1000);
        for (List<String> splitList : splitOrderIdList) {
            request.setOrderIdList(splitList);
            ordersResult.addAll(mapper.selectOrders(request));
        }
    }


    @Test
    public void test_listOrder_foreach2() throws IOException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        UserOrderRequest request = new UserOrderRequest();
        List<String> orderIdList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            orderIdList.add(String.valueOf(i));
        }
        List<List<String>> splitOrderIdList = Lists.partition(orderIdList, 2);
        request.setSplitOrderIdList(splitOrderIdList);
        List<Order> orders = mapper.selectOrders(request);
        log.info("输出结果：{}", orders);
    }
}