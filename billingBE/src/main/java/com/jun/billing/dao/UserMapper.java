package com.jun.billing.dao;

import com.jun.billing.entity.pojo.Menu;
import com.jun.billing.entity.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUser();

    @Select("select * from user where username = #{username} and password = #{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    List<Menu> getMenu(@Param("userId") Integer userId);
}
