package com.jun.billing.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderRequest {
    private Integer userId;
    private Integer page =1;
    private Integer size=10;
    private String orderBy = "date";
    private boolean sort = false;
    private Integer typeId;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
}
