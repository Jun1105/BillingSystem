package com.jun.billing.entity.dto;

import com.jun.billing.entity.pojo.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Integer total;
    private List<Order> orderList;
}
