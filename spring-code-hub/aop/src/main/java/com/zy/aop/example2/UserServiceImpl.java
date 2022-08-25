package com.zy.aop.example2;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void printUser(User user, String name) {
        System.out.println("--------------------------------");
        System.out.println(user);
        System.out.println(name);
    }
}
