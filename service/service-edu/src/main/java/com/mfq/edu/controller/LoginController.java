package com.mfq.edu.controller;

import com.mfq.edu.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/28 21:41
 * @description：登录认证
 * @modified By：
 * @version: v1$
 */
@Api( tags = "登录管理")
@RestController
@RequestMapping("/edu/user")
@CrossOrigin
public class LoginController {

    //login
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R info() {

        return R.ok().data("roles", "admin").data("name", "admin").data("avator", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
