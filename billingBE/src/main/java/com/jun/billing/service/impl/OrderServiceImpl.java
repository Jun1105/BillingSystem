package com.jun.billing.service.impl;

import com.jun.billing.dao.OrderMapper;
import com.jun.billing.entity.dto.OrderDto;
import com.jun.billing.entity.pojo.Order;
import com.jun.billing.entity.pojo.OrderCount;
import com.jun.billing.entity.pojo.TypeCount;
import com.jun.billing.entity.vo.OrderCountRequest;
import com.jun.billing.entity.vo.OrderRequest;
import com.jun.billing.service.OrderService;
import com.jun.billing.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
        if(orderCountRequest.getUserId() == null || orderCountRequest.getStartDate() == null || orderCountRequest.getEndDate() == null){
            return null;
        }
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

    @Override
    public List<TypeCount> getTypeCount(OrderCountRequest orderCountRequest) {
        if(orderCountRequest.getUserId() == null || orderCountRequest.getStartDate() == null || orderCountRequest.getEndDate() == null){
            return null;
        }
        return orderMapper.getTypeCount(orderCountRequest);
    }

    @Override
    public List<OrderCount> getWeekOrder(OrderCountRequest orderCountRequest) {
        if(orderCountRequest.getUserId() == null || orderCountRequest.getStartDate() == null || orderCountRequest.getEndDate() == null){
            return null;
        }
        List<OrderCount> orderCount = orderMapper.getOrderCount(orderCountRequest);
        //四周前
        List<LocalDate> fourWeekBefore = DateUtil.getLastDate(4);
        OrderCount orderCount4 = new OrderCount(fourWeekBefore.get(0), new BigDecimal(0));
        //三周前
        List<LocalDate> threeWeekBefore = DateUtil.getLastDate(3);
        OrderCount orderCount3 = new OrderCount(threeWeekBefore.get(0), new BigDecimal(0));
        //两周前
        List<LocalDate> twoWeekBefore = DateUtil.getLastDate(2);
        OrderCount orderCount2 = new OrderCount(twoWeekBefore.get(0), new BigDecimal(0));
        //一周前
        List<LocalDate> oneWeekBefore = DateUtil.getLastDate(1);
        OrderCount orderCount1 = new OrderCount(oneWeekBefore.get(0), new BigDecimal(0));
        //循环遍历数据库数据获取每周总共金额
        orderCount.stream().forEach(o->{
            if((fourWeekBefore.get(0).isBefore(o.getDate()) || fourWeekBefore.get(0).isEqual(o.getDate()))
                    && fourWeekBefore.get(1).isAfter(o.getDate()) || fourWeekBefore.get(1).isEqual(o.getDate())){
                if(o.getTotalAmount() !=null){
                    orderCount4.setTotalAmount(orderCount4.getTotalAmount().add(o.getTotalAmount()));
                }
            }else if((threeWeekBefore.get(0).isBefore(o.getDate()) || threeWeekBefore.get(0).isEqual(o.getDate()))
                    && (threeWeekBefore.get(1).isAfter(o.getDate()) || threeWeekBefore.get(1).isEqual(o.getDate()))){
                if(o.getTotalAmount() !=null){
                    orderCount3.setTotalAmount(orderCount3.getTotalAmount().add(o.getTotalAmount()));
                }
            } else if((twoWeekBefore.get(0).isBefore(o.getDate()) || twoWeekBefore.get(0).isEqual(o.getDate()))
                    && (twoWeekBefore.get(1).isAfter(o.getDate()) || twoWeekBefore.get(1).isEqual(o.getDate()))){
                if(o.getTotalAmount() !=null){
                    orderCount2.setTotalAmount(orderCount2.getTotalAmount().add(o.getTotalAmount()));
                }
            } else if((oneWeekBefore.get(0).isBefore(o.getDate()) || oneWeekBefore.get(0).isEqual(o.getDate()))
                    && (oneWeekBefore.get(1).isAfter(o.getDate()) || oneWeekBefore.get(1).isEqual(o.getDate()))){
                if(o.getTotalAmount() !=null){
                    orderCount1.setTotalAmount(orderCount1.getTotalAmount().add(o.getTotalAmount()));
                }
            }
        });
        List<OrderCount> orderCountList = new ArrayList<>();
        orderCountList.add(orderCount4);
        orderCountList.add(orderCount3);
        orderCountList.add(orderCount2);
        orderCountList.add(orderCount1);
        return orderCountList;
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
