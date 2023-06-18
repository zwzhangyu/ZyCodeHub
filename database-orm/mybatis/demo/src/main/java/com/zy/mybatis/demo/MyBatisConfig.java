//package com.zy.mybatis.demo;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//@Configuration
//public class MyBatisConfig  {
//
//    @Autowired
//    private List<SqlSessionFactory> sqlSessionFactoryList;
//
//    @PostConstruct
//    public void addMyInterceptor() {
//        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
//            sqlSessionFactory.getConfiguration().addInterceptor(new PrintExceptionSqlInterceptor());
//        }
//    }
//
//}
