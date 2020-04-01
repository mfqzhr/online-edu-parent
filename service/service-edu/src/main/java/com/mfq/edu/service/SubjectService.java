package com.mfq.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mfq.edu.entity.Subject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 添加课程分类
     *
     * @param file
     */
    void saveSubject(MultipartFile file, SubjectService subjectService);

    List<Subject> findTree();
}
