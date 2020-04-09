package com.mfq.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mfq.edu.commonutils.JwtUtils;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.UcenterMember;
import com.mfq.edu.entity.vo.RegisterVo;
import com.mfq.edu.service.UcenterMemberService;
import com.mfq.edu.vo.UcenterMemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 穆繁强
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
@Api(tags = "登录注册管理")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService memberService;

    //登录 注意 requestBody不能使用get提交
    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody UcenterMember member) {
        String token = memberService.login(member);
        return R.ok().data("token", token);
    }

    //注册
    @PostMapping("register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token字符串获取会员ID
    @GetMapping("getUserInfo")
    public R getUserInfo(HttpServletRequest request) {
        // 调用jwt工具类里面的方法
        String id = JwtUtils.getMemberIdByJwtToken(request);
        // 查询数据库
        UcenterMember member = memberService.getById(id);
        return R.ok().data("userInfo", member);
    }

    //根据用户id获取用户信息
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberVo getUserInfoOrder(@PathVariable("id") String id) {
        UcenterMember obj = memberService.getById(id);
        UcenterMemberVo target = new UcenterMemberVo();
        BeanUtils.copyProperties(obj, target);
        return target;
    }

    // 查询某一天的注册人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable("day") String day) {
        int count = memberService.count(new QueryWrapper<UcenterMember>().eq("gmt_create", day));
        return R.ok().data("countRegister", count);
    }

}

