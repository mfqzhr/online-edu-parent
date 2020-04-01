package com.mfq.edu.controller;


import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.Course;
import com.mfq.edu.entity.vo.CourseInfoVo;
import com.mfq.edu.entity.vo.CoursePublishVo;
import com.mfq.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
@Api(tags = "课程管理")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("")
    @ApiOperation("添加课程基本信息")
    public R addCourseInfo(@ApiParam(name = "courseInfoVo", value = "课程基本信息实体") @RequestBody CourseInfoVo courseInfoVo) {
        // 返回添加之后的课程id,为了后面添加课程大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }
    @PutMapping("")
    @ApiOperation("修改课程基本信息")
    public R updateCourseInfo(@ApiParam(name = "courseInfoVo", value = "课程基本信息实体") @RequestBody CourseInfoVo courseInfoVo) {
        // 返回添加之后的课程id,为了后面添加课程大纲使用
        String id = courseService.updateCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }


    @GetMapping("/{id}")
    @ApiOperation("根据课程ID查询课程基本信息")
    public R getCourseInfo(@PathVariable("id") String id) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(id);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    @GetMapping("/getPublishCourseInfo/{id}")
    @ApiOperation("根据课程ID查询课程最终页面信息")
    public R getPublishCourseInfo(@PathVariable("id") String id) {
        CoursePublishVo publishVo = courseService.getPublishCourseInfo(id);
        return R.ok().data("publishCourseInfo", publishVo);
    }

    @PostMapping("/publishCourse/{id}")
    @ApiOperation("最终发布课程")
    public R publishCourse(@PathVariable("id") String id){
        Course course = new Course();
        course.setId(id);
        course.setStatus("Normal");
        courseService.updateById(course);
        return R.ok();
    }
}

