package com.jun.billing.dao;

import com.jun.billing.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getAllUser();

    @Select("select * from user where username = #{username} and password = #{password}")
    public User getUser(String username, String password);
}
