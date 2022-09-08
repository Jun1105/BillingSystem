package com.jun.billing.controller;

import com.jun.billing.service.CommonService;
import com.jun.billing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Common {
    @Autowired
    CommonService commonService;
    @GetMapping("/getType")
    public Result getTypeList(){
        return Result.success("success", commonService.getTypeList());
    }
}
