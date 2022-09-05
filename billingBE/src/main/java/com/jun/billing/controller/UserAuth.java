package com.jun.billing.controller;

import com.jun.billing.service.MenuService;
import com.jun.billing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuth {
    @Autowired
    public MenuService menuService;
    @PostMapping("/getMenu/{userId}")
    public Result getMenu(@PathVariable Integer userId){
        return Result.success("success",menuService.getMenu(userId));
    }
}
