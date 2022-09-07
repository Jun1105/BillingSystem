package com.jun.billing.service.impl;

import com.jun.billing.dao.OrderMapper;
import com.jun.billing.entity.dto.OrderDto;
import com.jun.billing.entity.pojo.Order;
import com.jun.billing.entity.vo.OrderRequest;
import com.jun.billing.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    public OrderMapper orderMapper;
    @Override
    public OrderDto getUserAllOrder(OrderRequest req) {
        if(req.getPage() == null && req.getSize() == null){
            return null;
        }
        if(req.getPage() != null && req.getSize() != null){
            Integer page = (req.getPage()-1) * req.getSize();
            req.setPage(page);
        }
        OrderDto orderDto = new OrderDto();
        List<Order> orderList = orderMapper.getUserAllOrder(req);
        orderDto.setOrderList(orderList);
        orderDto.setTotal(orderList.size());
        return orderDto;
    }

    @Override
    public Boolean insertOrder(Order req) {
        if(req.getTypeId() == null || req.getAmount() == null || req.getDate() == null){
            return false;
        }
        orderMapper.insertOrder(req);
        return true;
    }

    @Override
    public Boolean updateOrder(Order req) {
        if(req.getTypeId() != null && req.getAmount() != null && req.getDate() != null){
            orderMapper.updateOrder(req);
            return true;
        }
        return false;
    }
}
