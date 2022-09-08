package com.jun.billing.entity.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TypeCount {
    private String typeName;
    private BigDecimal totalAmount;
}
