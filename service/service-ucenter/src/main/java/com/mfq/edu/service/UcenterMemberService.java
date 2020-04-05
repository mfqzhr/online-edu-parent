package com.mfq.edu.service;

import com.mfq.edu.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mfq.edu.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-04-04
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);
}
