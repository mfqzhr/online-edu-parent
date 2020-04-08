package com.mfq.edu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mfq.edu.client.EduClient;
import com.mfq.edu.client.UcenterClient;
import com.mfq.edu.entity.Order;
import com.mfq.edu.mapper.OrderMapper;
import com.mfq.edu.service.OrderService;
import com.mfq.edu.vo.CourseWebVoOrder;
import com.mfq.edu.vo.UcenterMemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-04-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    // 通过远程调用获取用户信息
    // 通过远程调用获取课程信息
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrders(String courseId, String memberId) {
        CourseWebVoOrder courseDto = eduClient.getCourseInfoOrder(courseId);
        UcenterMemberVo ucenterMember = ucenterClient.getUserInfoOrder(memberId);
        Order order = new Order();
        order.setOrderNo(StrUtil.uuid().substring(0, 18)); //生成订单号
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseDto.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterMember.getMobile());
        order.setNickname(ucenterMember.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
