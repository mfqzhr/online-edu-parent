package com.mfq.edu.controller;

import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.Chapter;
import com.mfq.edu.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/31 11:45
 * @description：章节
 * @modified By：
 * @version: v1$
 */
@RestController
@Api(tags = "章节管理")
@CrossOrigin
@RequestMapping("/edu/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    //课程大纲列表
    @GetMapping("/{courseId}")
    @ApiOperation("获取章节信息")
    public R getChapterVideo(@ApiParam(name = "courseid", value = "课程ID") @PathVariable("courseId") String courseId) {
        List<Chapter> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("list", list);
    }

    @PutMapping("/put/{id}")
    public R updateChapter(@PathVariable("id") String id , @RequestBody Chapter chapter) {
        chapter.setId(id);
        chapterService.updateById(chapter);
        return R.ok();
    }

    @PostMapping()
    @ApiOperation("添加章节信息")
    public R addChapter(@ApiParam(name = "chapter", value = "章节实体信息") @RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return R.ok();
    }

    @GetMapping("")
    @ApiOperation("根据id查询章节信息")
    public R getChapter(@PathVariable("id") String id) {
        Chapter chapter = chapterService.getById(id);
        return R.ok().data("chapter", chapter);
    }



    @DeleteMapping("{id}")
    public R deleteChapter(@PathVariable("id") String id) {
        chapterService.removeChapterById(id);
        return R.ok();
    }
}
