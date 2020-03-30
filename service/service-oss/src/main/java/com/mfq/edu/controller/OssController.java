package com.mfq.edu.controller;

import com.mfq.edu.commonutils.R;
import com.mfq.edu.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/3/29 15:16
 * @description：oss
 * @modified By：
 * @version: v1$
 */
@RestController
@RequestMapping("oss/ ")
@CrossOrigin
@Api(tags = "阿里云OSS")
public class OssController {

    @Autowired
    private OssService ossService;
    @ApiOperation("上传文件的方法")
    @PostMapping
    public R uploadOssFile(@ApiParam(name = "file", value = "文件对象") MultipartFile file) throws IOException {
        //获取上传的文件
        String url = ossService.upLoadFileAvatar(file);
        return R.ok().data("url", url);
    }
}
