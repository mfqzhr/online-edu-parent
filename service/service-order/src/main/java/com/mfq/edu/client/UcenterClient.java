package com.mfq.edu.client;

import com.mfq.edu.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/6 17:04
 * @description：
 * @modified By：
 * @version: $
 */
@Component
@FeignClient(value = "service-ucenter")
public interface UcenterClient {


    //根据用户id获取用户信息
    @PostMapping("/ucenter/member/getUserInfoOrder/{id}")
    UcenterMemberVo getUserInfoOrder(@PathVariable("id") String id);
}