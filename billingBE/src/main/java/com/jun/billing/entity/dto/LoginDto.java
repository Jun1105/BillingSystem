package com.jun.billing.entity.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String token;
    private Integer id;
    private String username;
}
