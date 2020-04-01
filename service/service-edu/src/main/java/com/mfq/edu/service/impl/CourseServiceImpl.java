package com.mfq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mfq.edu.entity.Course;
import com.mfq.edu.entity.CourseDescription;
import com.mfq.edu.entity.vo.CourseInfoVo;
import com.mfq.edu.entity.vo.CoursePublishVo;
import com.mfq.edu.mapper.CourseMapper;
import com.mfq.edu.service.CourseDescriptionService;
import com.mfq.edu.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        return courseMapper.getPublishCourseInfo(id);
    }

    @Override
    public String updateCourseInfo(CourseInfoVo courseInfoVo) {
        Course course = new Course();
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo, course);
        BeanUtils.copyProperties(courseInfoVo, courseDescription);
        updateById(course);
        courseDescriptionService.updateById(courseDescription);
        return course.getId();
    }

    @Override
    public CourseInfoVo getCourseInfo(String id) {
        Course course = getOne(new QueryWrapper<Course>().eq("id", id));
        CourseDescription courseDescription = courseDescriptionService.getOne(new QueryWrapper<CourseDescription>().eq("id", id));
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);
        BeanUtils.copyProperties(courseDescription, courseInfoVo);
        return courseInfoVo;
    }

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1. 向课程表里添加课程的基本信息
        // 把CourseInfoVo转换成Course
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo, course);
        save(course);
        //2. 向课程简介表中添加课程的基本信息
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo, courseDescription);
        courseDescription.setId(course.getId());
        courseDescriptionService.save(courseDescription);
        return course.getId();
    }
}
