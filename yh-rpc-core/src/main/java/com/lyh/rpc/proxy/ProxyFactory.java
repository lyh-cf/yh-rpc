package com.lyh.rpc.proxy;

/*
 *@title ServiceProxyFactory
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/12 22:21
 */

import com.lyh.rpc.RpcApplication;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂（用于创建代理对象）
 */
public class ProxyFactory {

    /**
     * 根据服务类获取代理对象
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        if (RpcApplication.getRpcConfig().isMock()) {
            return getMockProxy(serviceClass);
        }
        /*
        public static Object newProxyInstance(ClassLoader loader,类加载器，直接通过需要代理的类获取就行
                                          Class<?>[] interfaces,目标类所实现的所有接口
                                          InvocationHandler h) 方法拦截处理器，可以在里面实现方法的增强
         */
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }

    /**
     * 根据服务类获取 Mock 代理对象
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy());
    }
}