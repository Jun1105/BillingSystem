package com.jun.billing.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(String msg, T data){
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
