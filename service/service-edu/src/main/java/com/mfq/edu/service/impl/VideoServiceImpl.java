package com.mfq.edu.service.impl;

import com.mfq.edu.entity.Video;
import com.mfq.edu.mapper.VideoMapper;
import com.mfq.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
