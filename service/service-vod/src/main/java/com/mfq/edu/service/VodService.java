package com.mfq.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/2 13:39
 * @description：点播服务
 * @modified By：
 * @version: v1$
 */
public interface VodService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    void removeVideoByIds(List<String> ids);
}
