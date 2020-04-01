package com.mfq.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/30 13:53
 * @description：课程分类信息
 * @modified By：
 * @version: v1$
 */
@Data
public class SubjectData {
    @ExcelProperty(value = "一级分类", index = 0)
    private String oneSubjectName;
    @ExcelProperty(value = "二级分类", index = 1)
    private String twoSubjectName;
}
