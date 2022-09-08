package com.jun.billing.dao;

import com.jun.billing.entity.pojo.Order;
import com.jun.billing.entity.pojo.OrderCount;
import com.jun.billing.entity.vo.OrderCountRequest;
import com.jun.billing.entity.vo.OrderRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> getUserAllOrder(@Param("req") OrderRequest req);

    Integer getOrderTotal(@Param("req") OrderRequest req);

    Integer insertOrder(@Param("req") Order req);

    void updateOrder(@Param("req") Order req);

    void deleteOrder(@Param("orderId") Integer orderId);

    List<OrderCount> getOrderCount(@Param("req") OrderCountRequest orderCount);
}
