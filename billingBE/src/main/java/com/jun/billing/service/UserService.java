package com.jun.billing.service;

import com.jun.billing.entity.pojo.RoleMenu;
import com.jun.billing.entity.pojo.User;
import com.jun.billing.entity.pojo.UserRole;
import com.jun.billing.entity.vo.RoleMenuRequest;

import java.util.List;

public interface UserService {
    List<UserRole> getAllUser();
    User getUser(String username, String password);

    Boolean addUser(UserRole userRole);

    Boolean updateUser(UserRole userRole);

    Boolean updateRoleMenu(RoleMenuRequest roleMenuRequest);

    List<RoleMenu> roleMenu(RoleMenuRequest roleMenuRequest);

    Boolean updatePassword(User user);
}
