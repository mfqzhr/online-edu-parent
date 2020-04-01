package com.mfq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mfq.edu.config.handler.EduException;
import com.mfq.edu.entity.Chapter;
import com.mfq.edu.entity.Video;
import com.mfq.edu.mapper.ChapterMapper;
import com.mfq.edu.service.ChapterService;
import com.mfq.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    @Autowired
    private VideoService videoService;

    // 删除章节的方法
    // 如果章节有小节不能删除
    @Override
    public void removeChapterById(String id) {
        int count = videoService.count(new QueryWrapper<Video>().eq("chapter_id", id));
        if (count == 0) {
            removeById(id);
        } else {
            throw new EduException(20001, "请先删除子目录");
        }
    }

    @Override
    public List<Chapter> getChapterVideoByCourseId(String courseId) {
        List<Chapter> listChapter = list(new QueryWrapper<Chapter>().eq("course_id", courseId));
        List<Video> listVideo = videoService.list(null);
        for (Chapter chapter : listChapter) {
            List<Video> videos = listVideo.stream()
                    .filter(video -> chapter.getId().equals(video.getChapterId())).collect(Collectors.toList());
            chapter.setChildren(videos);
        }

        return listChapter;
    }
}
