package com.jun.billing.service.impl;

import com.jun.billing.dao.UserMapper;
import com.jun.billing.service.UserService;
import com.jun.billing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    public UserMapper userMapper;
    @Override
    public Result getAllUser() {
        return Result.success("成功", userMapper.getAllUser());
    }
}
