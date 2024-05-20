package com.lyh.rpc.springboot.starter.annotation;

/*
 *@title EnableRpc
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/20 20:47
 */

import com.lyh.rpc.springboot.starter.bootstrap.RpcConsumerBootstrap;
import com.lyh.rpc.springboot.starter.bootstrap.RpcInitBootstrap;
import com.lyh.rpc.springboot.starter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用 Rpc 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * 需要启动 server
     *
     * @return
     */
    boolean needServer() default true;
}