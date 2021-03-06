package com.mfq.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.Chapter;
import com.mfq.edu.entity.Course;
import com.mfq.edu.entity.CourseQueryVo;
import com.mfq.edu.entity.Subject;
import com.mfq.edu.entity.vo.CourseWebVo;
import com.mfq.edu.service.ChapterService;
import com.mfq.edu.service.CourseService;
import com.mfq.edu.service.SubjectService;
import com.mfq.edu.vo.CourseWebVoOrder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/6 1:12
 * @description：
 * @modified By：
 * @version: $
 */
@CrossOrigin
@RequestMapping("edu/coursefront")
@RestController
public class CourseFrontController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ChapterService chapterService;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery) {
        Page<Course> pageParam = new Page<Course>(page, limit);
        Map<String, Object> map = courseService.pageListWeb(pageParam, courseQuery);
        return R.ok().data(map);
    }

    @GetMapping("tree")
    @ApiOperation("树形课程信息")
    public R findTree() {
        List<Subject> list = subjectService.findTree();
        return R.ok().data("items", list);
    }

    @GetMapping("/{courseId}")
    public R getFrontCourseInfo(@PathVariable("courseId") String courseId) {
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);
        List<Chapter> chapters = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("course", courseWebVo).data("chapterVolist", chapters);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id) {
        CourseWebVo courseWebVo = courseService.selectInfoWebById(id);
        CourseWebVoOrder target = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseWebVo, target);
        return target;
    }

}
