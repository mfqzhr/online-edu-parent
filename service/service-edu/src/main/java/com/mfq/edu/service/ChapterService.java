package com.mfq.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mfq.edu.entity.Chapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
public interface ChapterService extends IService<Chapter> {

    List<Chapter> getChapterVideoByCourseId(String courseId);

    void removeChapterById(String id);

    boolean removeByCourseId(String id);
}
