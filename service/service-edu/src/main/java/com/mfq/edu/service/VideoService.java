package com.mfq.edu.service;

import com.mfq.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
public interface VideoService extends IService<Video> {

    boolean removeByCourseId(String id);
}
