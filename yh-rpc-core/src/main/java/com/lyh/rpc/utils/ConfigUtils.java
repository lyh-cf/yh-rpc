package com.lyh.rpc.utils;

/*
 *@title ConfigUtils
 *@description
 *@author LYH
 *@version 1.0
 *@create 2024/5/13 16:17
 */

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * 配置工具类
 */
public class ConfigUtils {

    /**
     * 加载配置对象
     *
     * @param tClass
     * @param prefix
     * @param <T>
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置对象，支持区分环境
     *
     * @param tClass
     * @param prefix
     * @param environment
     * @param <T>
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {

        StringBuilder configFileBuilder = new StringBuilder("application");
        // 如果环境不为空，将其附加到配置文件名称中
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        // 将文件扩展名附加到配置文件名称中
        configFileBuilder.append(".properties");
        // 创建一个 Props 对象来处理属性文件
        Props props = new Props(configFileBuilder.toString());
        /*
        创建了指定类（beanClass）的一个新实例，然后调用 fillBean 方法来用属性填充这个对象
        如果没有任何符合前缀条件的属性，则方法会直接返回传入的 bean 对象，而不做任何修改
        如果有，则尝试使用反射在提供的 bean 对象上设置属性
         */
        return props.toBean(tClass, prefix);
    }
}