package com.mfq.edu.controller;


import com.alibaba.excel.util.StringUtils;
import com.mfq.edu.client.VodClient;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.Video;
import com.mfq.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/edu/video")
@Api(tags = "小节管理")
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private VodClient vodClient;

    @ApiOperation("添加小节")
    @PostMapping
    public R addVideo(@RequestBody Video video) {
        videoService.save(video);
        return R.ok();
    }

    @ApiOperation("删除小节")
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable("id") String id) {
        Video video = videoService.getById(id);
        if (StringUtils.isEmpty(video.getVideoSourceId())) {
            vodClient.removeVideo(video.getVideoSourceId());
        }
        videoService.removeById(id);
        return R.ok();
    }

    @ApiOperation("批量删除小节")
    @DeleteMapping("deleteBatch")
    public R removeBatch(@RequestParam List<String> ids) {
        List<String> stringList = videoService.listByIds(ids).stream().map(Video::getVideoSourceId).collect(Collectors.toList());
        vodClient.removeBath(stringList);
        return R.ok().message("删除成功");
    }

    @ApiOperation("修改小节")
    @PutMapping("{id}")
    public R editVideo(@PathVariable("id") String id, @RequestBody Video video) {
        video.setId(id);
        videoService.updateById(video);
        return R.ok();
    }

    @ApiOperation("根据ID查询小节")
    @GetMapping("{id}")
    public R getVideo(@PathVariable("id") String id) {
        Video video = videoService.getById(id);
        return R.ok().data("video", video);
    }
}

