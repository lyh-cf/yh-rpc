package com.lyh.example.consumer;

import com.lyh.example.common.pojo.User;
import com.lyh.example.common.service.UserService;

/*
 *@title EasyConsumerExample
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/12 16:27
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        // todo 需要获取 UserService 的实现类对象
        UserService userService = null;
        User user = new User();
        user.setName("yh");
        //调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user==null");
        }
    }
}
