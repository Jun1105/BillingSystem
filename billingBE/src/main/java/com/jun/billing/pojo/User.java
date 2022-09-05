package com.jun.billing.pojo;

import lombok.Data;

import javax.annotation.Resource;

@Resource
@Data
public class User {
    private int id;
    private String username;
    private String password;
}
