package com.jun.billing.entity.pojo;

import lombok.Data;

import javax.annotation.Resource;

@Resource
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer role_id;
}
