package com.lyh.example.common.pojo;

import java.io.Serializable;

/*
 *@title User
 *@description 用户
 *@author LYH
 *@version 1.0
 *@create 2024/5/12 11:33
 */
public class User implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
