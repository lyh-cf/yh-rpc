package com.lyh.rpc.config;

/*
 *@title RpcConfig
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/13 16:16
 */

import lombok.Data;

/**
 * RPC 框架配置
 */
@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "yh-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;

}