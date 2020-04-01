package com.mfq.edu.mapper;

import com.mfq.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mfq.edu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 穆繁强
 * @since 2020-03-30
 */
public interface CourseMapper extends BaseMapper<Course> {
    CoursePublishVo getPublishCourseInfo(String id);
}
