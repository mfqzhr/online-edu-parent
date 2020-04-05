package com.mfq.edu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/5 0:24
 * @description：注册实体
 * @modified By：
 * @version: v1$
 */
@Data
@ApiModel("注册实体")
public class RegisterVo {

    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}
