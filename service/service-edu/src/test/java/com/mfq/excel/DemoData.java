package com.mfq.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/30 11:21
 * @description：excel操作
 * @modified By：
 * @version: v1$
 */
@Data
public class DemoData {
    @ExcelProperty("学生编号")
    private Integer sno;
    @ExcelProperty("学生姓名")
    private String sname;
}
