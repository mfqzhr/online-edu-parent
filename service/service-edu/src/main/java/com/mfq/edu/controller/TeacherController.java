package com.mfq.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.Teacher;
import com.mfq.edu.entity.vo.TeacherQuery;
import com.mfq.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-28
 */

@RestController
@RequestMapping("/edu/teacher")
@Api(tags = "讲师管理")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping
    public R list() {
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    @ApiOperation("根据id删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable("id") String id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("{id}")
    public R searchTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable("id") String id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    @ApiOperation("根据id修改讲师信息")
    @PutMapping("{id}")
    public R updateTeacher(
            @ApiParam(name = "id", value = "讲师id", required = true)
            @PathVariable("id") String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher) {
        teacher.setId(id);
        boolean b = teacherService.updateById(teacher);
        if (b) {
            return R.ok();
        }
        return R.error();
    }

    @ApiOperation("分页讲师列表")
    @PostMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象")
            @RequestBody(required = false) TeacherQuery teacherQuery //注意: 使用RequestBody提交必须使用Post提交
    ) {

        Page<Teacher> pageParam = new Page<>(page, limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("添加讲师")
    @PostMapping
    public R addTeacher(@ApiParam(name = "teacher", value = "讲师对象", required = true)
                        @RequestBody Teacher teacher) {
        boolean flag = teacherService.save(teacher);
        if (flag) {
            return R.ok();
        }
        return R.error();
    }


}

