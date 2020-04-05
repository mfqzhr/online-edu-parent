package com.mfq.edu.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mfq.edu.commonutils.JwtUtils;
import com.mfq.edu.config.handler.EduException;
import com.mfq.edu.entity.UcenterMember;
import com.mfq.edu.entity.vo.RegisterVo;
import com.mfq.edu.mapper.UcenterMemberMapper;
import com.mfq.edu.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-04-04
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 注册
     *
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        // 获取注册数据
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickName = registerVo.getNickName();
        String password = SecureUtil.md5(registerVo.getPassword());
        if (StringUtils.isEmpty(mobile)) {
            throw new EduException(20001, "手机号为空");
        }
        if (StringUtils.isEmpty(nickName)) {
            throw new EduException(20001, "昵称为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new EduException(20001, "密码为空");
        }
        if (StringUtils.isEmpty(code)) {
            throw new EduException();
        }
        String redisCode = redisTemplate.opsForValue().get(mobile);
        System.out.println(redisCode);
        if (!redisCode.equals(code)) {
            throw new EduException(20001, "验证码输入错误");
        }

        //判断手机号是否重复,表里面不能存在相同的手机号
        UcenterMember phone = getOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (phone != null) {
            throw new EduException(20001, "手机号已被注册");
        }
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile).setNickname(nickName)
                .setPassword(password).setIsDisabled(false).setAvatar("https://edu-online-oss.oss-cn-hangzhou.aliyuncs.com/51a20d33-7b22-409f-a515-d17f196e90c2file.png");
        save(member);

    }

    /**
     * 单点登录
     *
     * @param member
     * @return
     */
    @Override
    public String login(UcenterMember member) {
        // 获取登录手机号码
        String mobile = member.getMobile();
        String password = SecureUtil.md5(member.getPassword());

        // 手机号和密码非空的判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new EduException(20001, "用户名或者密码为空");
        }
        // 判断手机号是否正确
        UcenterMember one = getOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (null == one) {
            throw new EduException(20001, "用户名不正确");
        }
        // 判断密码是否正确
        if (!password.equals(one.getPassword())) {
            throw new EduException(20001, "密码不正确");
        }
        // 判断用户是否禁用
        if (one.getIsDisabled()) {
            throw new EduException(20001, "用户被禁用");
        }
        String token = JwtUtils.getJwtToken(one.getId(), one.getNickname());
        return token;
    }
}
