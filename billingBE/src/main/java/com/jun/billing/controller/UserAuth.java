package com.jun.billing.controller;

import com.jun.billing.entity.pojo.UserRole;
import com.jun.billing.entity.vo.RoleMenuRequest;
import com.jun.billing.service.MenuService;
import com.jun.billing.service.UserService;
import com.jun.billing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuth {
    @Autowired
    public MenuService menuService;
    @Autowired
    UserService userService;
    @PostMapping("/getMenu/{userId}")
    public Result getMenu(@PathVariable Integer userId){
        return Result.success("success",menuService.getMenu(userId));
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody UserRole userRole){
        return Result.success("success", userService.addUser(userRole));
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody UserRole userRole){
        return Result.success("success", userService.updateUser(userRole));
    }

    @PostMapping("/updateRole")
    public Result updateRole(@RequestBody RoleMenuRequest roleMenuRequest){
        return Result.success("success", userService.updateRoleMenu(roleMenuRequest));
    }

    @PostMapping("/roleMenu")
    public Result searchRole(@RequestBody RoleMenuRequest roleMenuRequest){
        return Result.success("success", userService.roleMenu(roleMenuRequest));
    }
}
