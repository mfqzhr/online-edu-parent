package com.mfq.edu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/28 14:54
 * @description：多条件查询
 * @modified By：
 * @version: v1$
 */
@Data
@ApiModel(value = "Teacher查询对象", description = "讲师查询对象封装")
public class TeacherQuery implements Serializable {
    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2020-3-28 14:58:22")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2020-3-28 14:58:22")
    private String end;
}
