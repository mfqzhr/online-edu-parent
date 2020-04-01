package com.mfq;

import com.alibaba.excel.EasyExcel;
import com.mfq.excel.DemoData;
import com.mfq.excel.ExcelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/30 12:58
 * @description：测试excel
 * @modified By：
 * @version: v1$
 */
public class TestExcel {

    public static void main(String[] args) {
      /*  //实现excel写操作
        //1. 设置写入文件夹地址和excel文件名称
        String filename = "F:\\write.xlsx";
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //2.调用easyexcel里面的实现方法实现写操作
        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());*/
        //实现读操作
        String filename = "F:\\write.xlsx";
        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSname("li" + i);
            data.setSno(i);
            list.add(data);
        }
        return list;
    }

}
