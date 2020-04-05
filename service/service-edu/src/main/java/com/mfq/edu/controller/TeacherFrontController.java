package com.mfq.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.Course;
import com.mfq.edu.entity.Teacher;
import com.mfq.edu.service.CourseService;
import com.mfq.edu.service.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/5 23:49
 * @description：前台接口
 * @modified By：
 * @version: v1$
 */
@RestController
@CrossOrigin
@RequestMapping("edu/teacherfront")
@Api(tags = "讲师前台接口")
@EnableDiscoveryClient
public class TeacherFrontController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        Page<Teacher> teacherPage = new Page<>(page, limit);
        Map<String, Object> data = teacherService.getTeacherFrontList(teacherPage);
        return R.ok().data(data);
    }

    @GetMapping("/{id}")
    public R getTeacherById(@PathVariable("id") String id) {
        Teacher teacher = teacherService.getById(id);
        List<Course> courses = courseService.list(new QueryWrapper<Course>().eq("teacher_id", id));
        return R.ok().data("teacher", teacher).data("courseList", courses);

    }
}
