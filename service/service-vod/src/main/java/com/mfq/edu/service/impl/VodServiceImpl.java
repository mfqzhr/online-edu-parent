package com.mfq.edu.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.mfq.edu.config.handler.EduException;
import com.mfq.edu.service.VodService;
import com.mfq.edu.utils.AliyunVodSDKUtils;
import com.mfq.edu.utils.ConstantPropertiesUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/2 13:40
 * @description：点播服务
 * @modified By：
 * @version: v1$
 */
@Service
public class VodServiceImpl implements VodService {
    /**
     * 上传视频文件
     *
     * @param file
     * @return
     */
    @Override
    public String uploadVideo(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String title = file.getOriginalFilename().substring(0, filename.lastIndexOf('.'));
        String videoId = "";
        try {
            videoId = uploadVideo(
                    ConstantPropertiesUtils.KEY_ID,
                    ConstantPropertiesUtils.KEY_SECRET,
                    title, filename, file.getInputStream()
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return videoId;
    }

    /**
     * 通过list集合删除视频
     *
     * @param ids
     */
    @Override
    public void removeVideoByIds(List<String> ids) {
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    ConstantPropertiesUtils.KEY_ID,
                    ConstantPropertiesUtils.KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            String ids_str = StringUtils.join(ids, ",");
            request.setVideoIds(ids_str);
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");

        } catch (ClientException e) {
            throw new EduException(20001, "视频删除失败");
        }
    }

    /**
     * 删除视频
     *
     * @param videoId
     */

    @Override
    public void removeVideo(String videoId) {
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                    ConstantPropertiesUtils.KEY_ID,
                    ConstantPropertiesUtils.KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();

            request.setVideoIds(videoId);

            DeleteVideoResponse response = client.getAcsResponse(request);

            System.out.print("RequestId = " + response.getRequestId() + "\n");

        } catch (ClientException e) {
            throw new EduException(20001, "视频删除失败");
        }
    }


    /**
     * 流式上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     * @param inputStream
     */
    private static String uploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName, InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return response.getVideoId();
    }
}
