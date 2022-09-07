package com.jun.billing.dao;

import com.jun.billing.entity.pojo.Order;
import com.jun.billing.entity.vo.OrderRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> getUserAllOrder(@Param("req") OrderRequest req);
}
