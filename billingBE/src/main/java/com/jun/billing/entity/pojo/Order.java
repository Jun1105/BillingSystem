package com.jun.billing.entity.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Order {
    private int orderId;
    private int userId;
    private int typeId;
    private String typeName;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
}
