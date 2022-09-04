package com.jun.billing.service;

import com.jun.billing.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public User getUser(String username, String password);
}
