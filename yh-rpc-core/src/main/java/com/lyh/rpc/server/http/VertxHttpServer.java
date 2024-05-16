package com.lyh.rpc.server.http;

import com.lyh.rpc.server.Server;
import com.lyh.rpc.server.http.HttpServerHandler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/*
 *@title VertxHttpServer
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/12 16:44
 */
/**
 * Vertx HTTP 服务器
 */
public class VertxHttpServer implements Server {

    /**
     * 启动服务器
     *
     * @param port
     */
    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 HTTP 服务器
        HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        // 启动 HTTP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server: " + result.cause());
            }
        });
    }
}