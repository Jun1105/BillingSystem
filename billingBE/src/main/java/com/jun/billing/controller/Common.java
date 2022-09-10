package com.jun.billing.controller;

import com.jun.billing.entity.pojo.Type;
import com.jun.billing.service.CommonService;
import com.jun.billing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Common {
    @Autowired
    CommonService commonService;
    @GetMapping("/getType")
    public Result getTypeList(){
        return Result.success("success", commonService.getTypeList());
    }

    @PostMapping("/addType")
    public Result addType(@RequestBody Type typeO){
        return Result.success("success", commonService.addTypeName(typeO));
    }

    @PostMapping("/updateType")
    public Result updateType(@RequestBody Type typeO){
        return Result.success("success", commonService.updateTypeName(typeO));
    }
}
