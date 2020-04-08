package com.mfq.edu.service;

import com.mfq.edu.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-04-06
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberId);
}
