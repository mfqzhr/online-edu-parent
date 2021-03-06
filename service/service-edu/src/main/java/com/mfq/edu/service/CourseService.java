package com.mfq.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mfq.edu.entity.Course;
import com.mfq.edu.entity.CourseQuery;
import com.mfq.edu.entity.CourseQueryVo;
import com.mfq.edu.entity.vo.CourseInfoVo;
import com.mfq.edu.entity.vo.CoursePublishVo;
import com.mfq.edu.entity.vo.CourseWebVo;

import java.util.Map;

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

    void pageQuery(Page<Course> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);

    Map<String, Object> pageListWeb(Page<Course> pageParam, CourseQueryVo courseQuery);

    CourseWebVo selectInfoWebById(String courseId);

    /**
     * 更新课程浏览数
     * @param id
     */
    void updatePageViewCount(String id);
}
