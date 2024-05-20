package com.lyh.example.consumer;

import com.lyh.example.common.pojo.User;
import com.lyh.example.common.service.UserService;
import com.lyh.rpc.bootstrap.ConsumerBootstrap;
import com.lyh.rpc.proxy.ProxyFactory;

/*
 *@title ConsumerExample2
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/20 16:26
 */
public class ConsumerExample2 {
    public static void main(String[] args) {
        // 服务提供者初始化
        ConsumerBootstrap.init();

        // 获取代理
        UserService userService = ProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("yh");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
