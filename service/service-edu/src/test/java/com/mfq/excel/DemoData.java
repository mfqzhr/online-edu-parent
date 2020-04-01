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
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;
}
