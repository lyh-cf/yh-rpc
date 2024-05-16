package com.lyh.example.provider;

import com.lyh.example.common.service.UserService;
import com.lyh.rpc.RpcApplication;
import com.lyh.rpc.registry.LocalRegistry;
import com.lyh.rpc.server.Server;
import com.lyh.rpc.server.http.VertxHttpServer;

/*
 *@title EasyProviderExample
 *@description 简易服务提供者示例
 *@author LYH
 *@version 1.0
 *@create 2024/5/12 11:53
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        Server httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
