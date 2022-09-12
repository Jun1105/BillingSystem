package com.jun.billing.controller;

import com.jun.billing.entity.pojo.Menu;
import com.jun.billing.service.MenuService;
import com.jun.billing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {
    @Autowired
    MenuService menuService;

    @PostMapping("/addMenu")
    public Result addMenu(@RequestBody Menu menu){
        return Result.success("success", menuService.addMenu(menu));
    }

    @PostMapping("/updateMenu")
    public Result updateMenu(@RequestBody Menu menu){
        return Result.success("success", menuService.updateMenu(menu));
    }

    @PostMapping("/deleteMenu")
    public Result deleteMenu(@RequestBody Menu menu){
        return Result.success("success", menuService.deleteMenu(menu));
    }
}
