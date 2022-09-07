package com.jun.billing.dao;

import com.jun.billing.entity.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> getUserAllOrder(@Param("userId") int userId);
}
