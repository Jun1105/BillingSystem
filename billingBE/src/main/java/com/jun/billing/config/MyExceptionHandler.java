package com.jun.billing.config;


import com.jun.billing.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e){
        e.printStackTrace();
        return Result.error("获取失败");
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result runtimeExceptionHandler(Exception e){
        e.printStackTrace();
        return Result.errorToken("token失效，请重新登录！");
    }
}


