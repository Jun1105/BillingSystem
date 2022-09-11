package com.jun.billing.service.impl;

import com.jun.billing.dao.UserMapper;
import com.jun.billing.entity.pojo.RoleMenu;
import com.jun.billing.entity.pojo.User;
import com.jun.billing.entity.pojo.UserAdd;
import com.jun.billing.entity.pojo.UserRole;
import com.jun.billing.entity.vo.RoleMenuRequest;
import com.jun.billing.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    public UserMapper userMapper;
    @Override
    public List<UserRole> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUser(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            return null;
        }
        return userMapper.getUser(username, password);
    }

    @Override
    public Boolean addUser(UserRole userRole) {
        if(userRole.getUsername() == null || userRole.getRoleId() == null || userRole.getId() != null){
            return false;
        }
        UserAdd userAdd = new UserAdd();
        userAdd.setRole_id(userRole.getRoleId());
        userAdd.setUsername(userRole.getUsername());
        userMapper.addUser(userAdd);
        return true;
    }

    public Boolean updateUser(UserRole userRole) {
        if(userRole.getUsername() == null || userRole.getRoleId() == null || userRole.getId() == null){
            return false;
        }
        UserAdd userAdd = new UserAdd();
        userAdd.setRole_id(userRole.getRoleId());
        userAdd.setUsername(userRole.getUsername());
        userAdd.setId(userRole.getId());
        userMapper.updateUser(userAdd);
        return true;
    }

    @Override
    public Boolean updateRoleMenu(RoleMenuRequest roleMenuRequest) {
        if(roleMenuRequest.getRoleId() == null){
            return false;
        }
        List<RoleMenu> roleMenus = new ArrayList<>();
        roleMenuRequest.getMenuIdList().stream().forEach(o->{
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRole_id(roleMenuRequest.getRoleId());
            roleMenu.setMenu_id(o);
            roleMenus.add(roleMenu);
        });
        userMapper.deleteRoleMenu(roleMenuRequest);
        if(roleMenus.size() != 0){
            userMapper.insertRoleMenus(roleMenus);
        }
        return true;
    }

    @Override
    public List<RoleMenu> roleMenu(RoleMenuRequest roleMenuRequest) {
        if(roleMenuRequest.getRoleId() == null){
            return null;
        }
        List<RoleMenu> roleMenus = userMapper.roleMenu(roleMenuRequest);
        return roleMenus;
    }
}
