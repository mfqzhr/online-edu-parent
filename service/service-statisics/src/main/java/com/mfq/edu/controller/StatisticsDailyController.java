package com.mfq.edu.controller;


import com.mfq.edu.commonutils.R;
import com.mfq.edu.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author 穆繁强
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/statistics/daily")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    // 统计某一天的注册人数
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable("day") String day) {
        statisticsDailyService.registerCount(day);
        return R.ok();
    }

    // 图表显示, 返回两部分数据, 日期json数组,数量json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable(value = "type", required = false) String type, @PathVariable(value = "begin", required = false) String begin, @PathVariable(value = "end", required = false) String end) {
        Map<String, Object> map = statisticsDailyService.getShowData(type, begin, end);
        return R.ok().data(map);
    }
}

