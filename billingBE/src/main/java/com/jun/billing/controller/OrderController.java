package com.jun.billing.controller;

import com.jun.billing.entity.pojo.Order;
import com.jun.billing.entity.vo.OrderCountRequest;
import com.jun.billing.entity.vo.OrderRequest;
import com.jun.billing.service.OrderService;
import com.jun.billing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController {
    @Autowired
    public OrderService orderService;

    @PostMapping("/getAllOrder")
    public Result getUserAllOrder(@RequestBody OrderRequest req){
        return Result.success("success", orderService.getUserAllOrder(req));
    }

    @PostMapping("/addOrder")
    public Result insertOrder(@RequestBody Order req){
        return Result.success("success", orderService.insertOrder(req));
    }

    @PostMapping("/updateOrder")
    public Result updateOrder(@RequestBody Order req){
        return Result.success("success", orderService.updateOrder(req));
    }

    @PostMapping("/deleteOrder")
    public Result deleteOrder(@RequestBody Order req){
        return Result.success("success", orderService.deleteOrder(req.getOrderId()));
    }

    @PostMapping("/getOrderCount")
    public Result getOrderCount(@RequestBody OrderCountRequest orderCountRequest){
        return Result.success("success", orderService.getOrderCount(orderCountRequest));
    }

    @PostMapping("/getTypeCount")
    public Result getTypeCount(@RequestBody OrderCountRequest orderCountRequest){
        return Result.success("success", orderService.getTypeCount(orderCountRequest));
    }

    @PostMapping("/getWeekOrder")
    public Result getWeekOrder(@RequestBody OrderCountRequest orderCountRequest){
        return Result.success("success", orderService.getWeekOrder(orderCountRequest));
    }
}
