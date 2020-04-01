package com.mfq.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/30 13:19
 * @description：excel读监听
 * @modified By：
 * @version: v1$
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
    // 一行一行读取excel内容
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("***" + demoData);
    }

    // 读取表头内容


    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头: " + headMap);
    }

    //读取完成之后的内容
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
