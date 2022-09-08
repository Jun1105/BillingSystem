package com.jun.billing.service;

import com.jun.billing.entity.dto.OrderDto;
import com.jun.billing.entity.pojo.Order;
import com.jun.billing.entity.pojo.OrderCount;
import com.jun.billing.entity.pojo.TypeCount;
import com.jun.billing.entity.vo.OrderCountRequest;
import com.jun.billing.entity.vo.OrderRequest;

import java.util.List;

public interface OrderService {
    OrderDto getUserAllOrder(OrderRequest req);

    Boolean insertOrder(Order req);

    Boolean updateOrder(Order req);

    Boolean deleteOrder(Integer orderId);

    List<OrderCount> getOrderCount(OrderCountRequest orderCountRequest);

    List<TypeCount> getTypeCount(OrderCountRequest orderCountRequest);

    List<OrderCount> getWeekOrder(OrderCountRequest orderCountRequest);
}
