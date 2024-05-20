package com.lyh.example.provider;

import com.lyh.example.common.service.UserService;
import com.lyh.rpc.bootstrap.ProviderBootstrap;
import com.lyh.rpc.model.ServiceRegisterInfo;

import java.util.ArrayList;
import java.util.List;

/*
 *@title ProviderExample2
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/20 16:21
 */
/**
 * 服务提供者示例
 */
public class ProviderExample2 {
    public static void main(String[] args) {
        // 要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo<UserService> serviceRegisterInfo = new ServiceRegisterInfo<>(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
