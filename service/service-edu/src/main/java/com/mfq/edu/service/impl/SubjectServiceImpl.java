package com.mfq.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mfq.edu.config.handler.EduException;
import com.mfq.edu.entity.Subject;
import com.mfq.edu.entity.excel.SubjectData;
import com.mfq.edu.entity.excel.listener.SubjectExcelListener;
import com.mfq.edu.mapper.SubjectMapper;
import com.mfq.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            //文件输入流
            InputStream inputStream = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (EduException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Subject> findTree() {
        List<Subject> subjects = subjectMapper.selectList(null);
        List<Subject> res = new ArrayList<>();
        Map<String, Subject> map = new HashMap<>();
        for (Subject subject : subjects) {
            map.put(subject.getId(), subject);
        }
        for (Subject subject : subjects) {
            if (subject.getParentId().equals("0")) {
                res.add(subject);
            } else {
                Subject parent = map.get(subject.getParentId());
                parent.getChildren().add(subject);
            }
        }
        clearEmpty(res);
        return res;
    }

    private void clearEmpty(List<Subject> subjects) {
        if (subjects == null) {
            return;
        }
        for (Subject subject : subjects) {
            if (subject.getChildren().size() == 0) {
                subject.setChildren(null);
            }
            clearEmpty(subject.getChildren());
        }
    }
}
