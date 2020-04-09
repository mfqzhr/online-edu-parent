package com.mfq.edu.client;

import com.mfq.edu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/9 10:22
 * @description：
 * @modified By：
 * @version: $
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @GetMapping("/ucenter/member/countRegister/{day}")
    R countRegister(@PathVariable("day") String day);
}
