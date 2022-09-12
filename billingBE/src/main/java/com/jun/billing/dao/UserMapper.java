package com.jun.billing.dao;

import com.jun.billing.entity.pojo.*;
import com.jun.billing.entity.vo.RoleMenuRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    List<Menu> getAllMenu();
    List<UserRole> getAllUser();

    @Select("select * from user where username = #{username} and password = #{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

    List<Menu> getMenu(@Param("userId") Integer userId);

    @Insert("insert into `user`(username, password, role_id) values(#{req.username},123456,#{req.role_id})")
    void addUser(@Param("req") UserAdd userAdd);

    @Update("update user set user.username = #{req.username}, user.role_id = #{req.role_id} where user.id = #{req.id}")
    void updateUser(@Param("req") UserAdd userAdd);

    @Select("select role_id,menu_id from role_menu where role_id = #{role.roleId}")
    List<RoleMenu> roleMenu(@Param("role") RoleMenuRequest role);

    @Delete("delete from role_menu where role_id = #{role.roleId}")
    void deleteRoleMenu(@Param("role") RoleMenuRequest roleMenuRequest);

    void insertRoleMenus(@Param("roleMenuList") List<RoleMenu> roleMenus);

    @Update("update user set user.password = #{req.password} where user.id = #{req.id}")
    void updatePassword(@Param("req") User user);
}
