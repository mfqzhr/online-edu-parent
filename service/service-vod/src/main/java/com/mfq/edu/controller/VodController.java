package com.mfq.edu.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.config.handler.EduException;
import com.mfq.edu.service.VodService;
import com.mfq.edu.utils.AliyunVodSDKUtils;
import com.mfq.edu.utils.ConstantPropertiesUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/2 13:37
 * @description：视频点播服务
 * @modified By：
 * @version: v1$
 */
@RestController
@RequestMapping("/vod")
@Api(tags = "视频点播服务")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("上传视屏到阿里云")
    @PostMapping("/upload")
    public R uploadVideo(MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId", videoId);
    }

    @ApiOperation("根据视频ID删除视频")
    @DeleteMapping("/remove/{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable("videoId") String videoId) {
        vodService.removeVideo(videoId);
        return R.ok().message("视频删除成功");
    }

    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable("id") String id) {
        try {
            //创建初始化对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET);
            //创建凭证
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            //调用方法
            GetVideoPlayAuthResponse res = client.getAcsResponse(request);
            String playAuth = res.getPlayAuth();
            System.out.println(res);
            return R.ok().data("playAuth", playAuth);
        } catch (EduException | ClientException e) {
            throw new EduException(200001, "没有授权");
        }
    }
}
