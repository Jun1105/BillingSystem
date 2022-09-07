package com.jun.billing.service;

import com.jun.billing.entity.pojo.Order;
import com.jun.billing.entity.vo.OrderRequest;

import java.util.List;

public interface OrderService {
    List<Order> getUserAllOrder(OrderRequest req);

    Boolean insertOrder(Order req);

    Boolean updateOrder(Order req);
}
