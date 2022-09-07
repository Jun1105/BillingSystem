package com.jun.billing.controller;

import com.jun.billing.entity.pojo.Order;
import com.jun.billing.entity.vo.OrderRequest;
import com.jun.billing.service.OrderService;
import com.jun.billing.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
