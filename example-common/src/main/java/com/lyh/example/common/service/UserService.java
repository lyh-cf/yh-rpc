package com.lyh.example.common.service;

import com.lyh.example.common.pojo.User;

/*
 *@title UserService
 *@description 用户服务
 *@author LYH
 *@version 1.0
 *@create 2024/5/12 11:39
 */
public interface UserService {
    /**
     * 获取用户
     */
    User getUser(User user);
}
