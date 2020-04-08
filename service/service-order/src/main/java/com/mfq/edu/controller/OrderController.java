package com.mfq.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mfq.edu.commonutils.JwtUtils;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.Order;
import com.mfq.edu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 穆繁强
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;


    //1, 生成订单
    @PostMapping("/createOrder/{courseId}")
    public R createOrder(@PathVariable("courseId") String courseId, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrders(courseId, memberId);
        return R.ok().data("orderNo", orderNo);
    }

    //2, 根据订单ID查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable("orderId") String orderId) {
        Order order = orderService.getOne(new QueryWrapper<Order>().eq("order_no", orderId));
        return R.ok().data("item", order);
    }
}

