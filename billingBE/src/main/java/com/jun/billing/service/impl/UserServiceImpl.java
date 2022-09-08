package com.jun.billing.service.impl;

import com.jun.billing.dao.UserMapper;
import com.jun.billing.entity.pojo.User;
import com.jun.billing.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    public UserMapper userMapper;
    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUser(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            return null;
        }
        return userMapper.getUser(username, password);
    }
}
