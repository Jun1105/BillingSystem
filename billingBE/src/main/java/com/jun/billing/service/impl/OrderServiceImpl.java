package com.jun.billing.service.impl;

import com.jun.billing.dao.OrderMapper;
import com.jun.billing.entity.dto.OrderDto;
import com.jun.billing.entity.pojo.Order;
import com.jun.billing.entity.pojo.OrderCount;
import com.jun.billing.entity.vo.OrderCountRequest;
import com.jun.billing.entity.vo.OrderRequest;
import com.jun.billing.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        orderDto.setTotal(orderMapper.getOrderTotal(req));
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

    @Override
    public Boolean deleteOrder(Integer orderId) {
        if(orderId != null){
            orderMapper.deleteOrder(orderId);
            return true;
        }
        return false;
    }

    @Override
    public List<OrderCount> getOrderCount(OrderCountRequest orderCountRequest) {
        List<OrderCount> orderCount = orderMapper.getOrderCount(orderCountRequest);
        List<OrderCount> orderCounts = new ArrayList<>();
        List<LocalDate> weekPeriod = getWeekPeriod(orderCountRequest.getStartDate());
        for (LocalDate localDate : weekPeriod) {
            OrderCount count = new OrderCount(localDate,new BigDecimal(0));
            Optional<OrderCount> countDb = orderCount.stream().filter(o -> o.getDate().equals(localDate)).findFirst();
            if(countDb.isPresent()){
                OrderCount count1 = countDb.get();
                count.setTotalAmount(count1.getTotalAmount());
            }
            orderCounts.add(count);
        }
        return orderCounts;
    }

    private List<LocalDate> getWeekPeriod(LocalDate startDate){
        List<LocalDate> weekPeriod = new ArrayList<>();
        weekPeriod.add(startDate);
        for (int i = 1; i < 7; i++) {
            LocalDate localDate = weekPeriod.get(i - 1);
            weekPeriod.add(localDate.plusDays(1));
        }
       return weekPeriod;
    }
}
