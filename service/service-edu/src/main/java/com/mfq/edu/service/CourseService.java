package com.mfq.edu.service;

import com.mfq.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mfq.edu.entity.vo.CourseInfoVo;
import com.mfq.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String id);

    String updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String id);
}
