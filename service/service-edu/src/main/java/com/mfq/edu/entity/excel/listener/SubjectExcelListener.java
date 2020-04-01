package com.mfq.edu.entity.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mfq.edu.config.handler.EduException;
import com.mfq.edu.entity.Subject;
import com.mfq.edu.entity.excel.SubjectData;
import com.mfq.edu.service.SubjectService;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/30 13:59
 * @description：
 * @modified By：
 * @version: $
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // 因为SubjectExcelListener不能交给spring管理,需要自己new,不能注入其他对象
    //不能实现数据库操作
    public SubjectService subjectService;

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (null == subjectData) {
            throw new EduException(20001, "文件数据为空");
        }
        //判断一级分类是否重复
        Subject one = existOneSubject(subjectData.getOneSubjectName(), subjectService);
        if (null == one) {
            one = new Subject();
            one.setParentId("0");
            one.setTitle(subjectData.getOneSubjectName());
            subjectService.save(one);
        }
        //判断二级分类是否重复
        Subject two = existTwoSubject(subjectData.getTwoSubjectName(), subjectService, one.getId());
        if (null == two) {
            two = new Subject();
            two.setParentId(one.getId());
            two.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(two);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //判断一级分类不能重复添加
    private Subject existOneSubject(String name, SubjectService subjectService) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name).eq("parent_id", "0");
        Subject one = subjectService.getOne(wrapper);
        return one;
    }

    //判断二级分类不能重复添加
    private Subject existTwoSubject(String name, SubjectService subjectService, String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name).eq("parent_id", pid);
        Subject two = subjectService.getOne(wrapper);
        return two;
    }

}
