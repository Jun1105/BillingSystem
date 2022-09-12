package com.jun.billing.controller;

import com.jun.billing.entity.pojo.User;
import com.jun.billing.service.UserService;
import com.jun.billing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Login {
    @Autowired
    public UserService userService;

    @GetMapping("/getAllUser")
    public Result getAllUser(){
        return Result.success("success", userService.getAllUser());
    }

    @PostMapping("/login")
    public Result getUser(@RequestBody User user){
        return Result.success("success",userService.getUser(user.getUsername(), user.getPassword()));
    }

    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody User user){
        return Result.success("success",userService.updatePassword(user));
    }
}
