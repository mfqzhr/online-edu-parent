package com.mfq.edu.config.handler;

import com.mfq.edu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/28 17:07
 * @description：统一异常处理类
 * @modified By：
 * @version: v1$
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //为了能够返回数据
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    //自定义异常处理执行这个方法
    @ExceptionHandler(EduException.class)
    @ResponseBody //为了能够返回数据
    public R error(EduException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
