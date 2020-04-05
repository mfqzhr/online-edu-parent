package com.mfq.edu.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/4 13:29
 * @description：短信服务
 * @modified By：
 * @version: v1$
 */
@RestController
@RequestMapping("msm/msm")
@CrossOrigin
@Api(tags = "短信服务")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //发送短信的方法
    @GetMapping("{phone}")
    @ApiOperation("发送短信验证码")
    public R senMsm(@PathVariable("phone") String phone) {

        String value = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(value)) {
            return R.ok();
        }
        String code = RandomUtil.randomString(4);
        Map<Object, Object> map = new HashMap<>();
        map.put("code", code);
        Boolean isSend = msmService.send(map, phone);
        if (isSend) {
            // 发送成功, 把发送的验证码放到redis中
            // 设置有效时间
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        } else {

            return R.error().message("发送短信失败");
        }
    }
}
