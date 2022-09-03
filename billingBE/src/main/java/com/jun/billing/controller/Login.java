package com.jun.billing.controller;

import com.jun.billing.pojo.User;
import com.jun.billing.service.UserService;
import com.jun.billing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Login {
    @Autowired
    public UserService userService;
    @PostMapping("/login")
    public Result hello(){
        return userService.getAllUser();
    }
}
