package com.jun.billing.dao;

import com.jun.billing.entity.pojo.Menu;
import com.jun.billing.entity.pojo.User;
import com.jun.billing.entity.pojo.UserAdd;
import com.jun.billing.entity.pojo.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserRole> getAllUser();

    @Select("select * from user where username = #{username} and password = #{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    List<Menu> getMenu(@Param("userId") Integer userId);

    @Insert("insert into `user`(username, password, role_id) values(#{req.username},123456,#{req.role_id})")
    void addUser(@Param("req") UserAdd userAdd);

    @Update("update user set user.username = #{req.username}, user.role_id = #{req.role_id} where user.id = #{req.id}")
    void updateUser(@Param("req") UserAdd userAdd);
}
