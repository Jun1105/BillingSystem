package com.jun.billing.entity.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Order {
    private Integer orderId;
    private Integer userId;
    private Integer typeId;
    private String typeName;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
}
