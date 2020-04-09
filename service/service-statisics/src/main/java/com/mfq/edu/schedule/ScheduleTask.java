package com.mfq.edu.schedule;

import cn.hutool.core.date.DateUtil;
import com.mfq.edu.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/9 12:33
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class ScheduleTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;
    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        statisticsDailyService.registerCount(DateUtil.yesterday().toDateStr());
    }
    
}
