package com.jun.billing.entity.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderCountRequest {
    private Integer userId;
    private LocalDate startDate;
    private LocalDate endDate;
}
