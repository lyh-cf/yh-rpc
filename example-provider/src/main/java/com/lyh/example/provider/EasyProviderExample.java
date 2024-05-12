package com.lyh.example.provider;

import com.lyh.example.common.service.UserService;
import com.lyh.rpc.registry.LocalRegistry;
import com.lyh.rpc.server.HttpServer;
import com.lyh.rpc.server.VertxHttpServer;

/*
 *@title EasyProviderExample
 *@description 简易服务提供者示例
 *@author LYH
 *@version 1.0
 *@create 2024/5/12 11:53
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
