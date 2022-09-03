package com.jun.billing.dao;

import com.jun.billing.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getAllUser();
}
