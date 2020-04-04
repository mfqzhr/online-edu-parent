package com.mfq.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.Course;
import com.mfq.edu.entity.Teacher;
import com.mfq.edu.service.CourseService;
import com.mfq.edu.service.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/4 0:12
 * @description：首页
 * @modified By：
 * @version: v1$
 */
@RestController
@RequestMapping("/edu/indexfront")
@CrossOrigin
@Api(tags = "首页")
public class IndexFrontController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;

    //查询前八条热门课程,和前四个热门讲师
    @GetMapping
    public R index() {
        List<Course> courses = courseService.list(new QueryWrapper<Course>()
                .orderByDesc("id").last("limit 8"));
        List<Teacher> teachers = teacherService.list(new QueryWrapper<Teacher>()
                .orderByDesc("id").last("limit 4"));
        return R.ok().data("courses", courses).data("teachers", teachers);
    }
}
