package com.lyh.rpc.model;

/*
 *@title ServiceRegisterInfo
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/20 16:20
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务注册信息类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRegisterInfo<T> {

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 实现类
     */
    private Class<? extends T> implClass;
}

