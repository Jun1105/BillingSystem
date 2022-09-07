package com.jun.billing.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderRequest {
    private int userId;
    private int page;
    private int size;
    private int orderBy;
    private String typeName;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
}
