package com.lyh.rpc.server;

/*
 *@title HttpServer
 *@description HTTP 服务器接口
 *@author LYH
 *@version 1.0
 *@create 2024/5/12 16:36
 */
/*
编写一个 web 服务器的接口 HttpServer，定义统一的启动服务器方法，便于后续的扩展，比如实现多种不同的 web 服务器。
 */
public interface HttpServer {
    /**
     * 启动服务器
     */
    void doStart(int port);
}
