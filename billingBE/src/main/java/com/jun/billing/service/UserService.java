package com.jun.billing.service;

import com.jun.billing.entity.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUser(String username, String password);
}
