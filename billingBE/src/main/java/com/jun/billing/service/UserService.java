package com.jun.billing.service;

import com.jun.billing.entity.pojo.User;
import com.jun.billing.entity.pojo.UserRole;

import java.util.List;

public interface UserService {
    List<UserRole> getAllUser();
    User getUser(String username, String password);

    Boolean addUser(UserRole userRole);

    Boolean updateUser(UserRole userRole);
}
