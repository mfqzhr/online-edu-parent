package com.mfq.edu.controller;


import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.Subject;
import com.mfq.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/subject")
@Api(tags = "课程分类管理")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //添加课程分类
    //获取上传过来的文件,把文件读取出来
    @PostMapping("")
    @ApiOperation("导入课程信息")
    public R addSubject(@ApiParam(name = "file", value = "上传表格") MultipartFile file) {
        //上传过来excel文件
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

    @GetMapping("")
    @ApiOperation("树形课程信息")
    public R findTree() {
        List<Subject> list = subjectService.findTree();
        return R.ok().data("list", list);
    }
}

