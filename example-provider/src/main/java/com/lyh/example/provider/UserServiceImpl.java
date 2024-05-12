package com.lyh.example.provider;

import com.lyh.example.common.pojo.User;
import com.lyh.example.common.service.UserService;

/*
 *@title UserServiceImpl
 *@description 用户服务实现类
 *@author LYH
 *@version 1.0
 *@create 2024/5/12 11:48
 */
public class UserServiceImpl implements UserService {
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
