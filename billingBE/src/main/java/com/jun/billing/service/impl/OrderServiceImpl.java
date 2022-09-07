package com.jun.billing.service.impl;

import com.jun.billing.dao.OrderMapper;
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
    public List<Order> getUserAllOrder(OrderRequest req) {
        if(req.getPage() != null && req.getSize() != null){
            Integer page = (req.getPage()-1) * req.getSize();
            req.setPage(page);
        }
        List<Order> orderList = orderMapper.getUserAllOrder(req);
        return orderList;
    }
}
