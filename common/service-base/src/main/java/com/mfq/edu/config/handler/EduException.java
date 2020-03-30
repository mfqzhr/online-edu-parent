package com.mfq.edu.config.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/28 19:16
 * @description：自定义异常处理
 * @modified By：
 * @version: v1$
 */
@Data
@AllArgsConstructor  //生成有参构造
@NoArgsConstructor   //生成无参构造
public class EduException extends RuntimeException {
    private Integer code; //状态码
    private String msg; //异常信息

}
