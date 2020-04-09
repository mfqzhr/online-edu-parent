package com.mfq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mfq.edu.client.UcenterClient;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.StatisticsDaily;
import com.mfq.edu.mapper.StatisticsDailyMapper;
import com.mfq.edu.service.StatisticsDailyService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author 穆繁强
 * @since 2020-04-08
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;


    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        List<StatisticsDaily> list = baseMapper.selectList(new QueryWrapper<StatisticsDaily>()
                .between("date_calculated", begin, end)
                .select("date_calculated", type)
        );
        List<String> dates = list.stream().map(StatisticsDaily::getDateCalculated).collect(Collectors.toList());
        List<Integer> numbers = null;
        switch (type) {
            case "register_num":
                numbers = list.stream().map(StatisticsDaily::getRegisterNum).collect(Collectors.toList());
                break;
            case "login_num":
                numbers = list.stream().map(StatisticsDaily::getLoginNum).collect(Collectors.toList());
                break;
            case "video_view_num":
                numbers = list.stream().map(StatisticsDaily::getVideoViewNum).collect(Collectors.toList());
                break;
            case "course_num":
                numbers = list.stream().map(StatisticsDaily::getCourseNum).collect(Collectors.toList());
                break;
            default:
                break;

        }
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList", dates);
        map.put("numDataList", numbers);
        return map;
    }

    @Override
    public void registerCount(String day) {
        remove(new QueryWrapper<StatisticsDaily>().eq("date_calculated", day));
        R r = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) r.getData().get("countRegister");
        //获取统计信息
        Integer loginNum = RandomUtils.nextInt(200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(200);//TODO
        Integer courseNum = RandomUtils.nextInt(200);//TODO

        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(countRegister);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        baseMapper.insert(daily);
    }
}
