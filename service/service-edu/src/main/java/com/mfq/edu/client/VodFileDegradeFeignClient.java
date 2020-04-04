package com.mfq.edu.client;

import com.mfq.edu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：穆繁强
 * @date ：Created in 2020/4/3 10:24
 * @description：熔断机制
 * @modified By：
 * @version: v1$
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {

    @Override
    public R removeVideo(String videoId) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R removeBath(List<String> ids) {
        return R.error().message("删除视频出错了");
    }
}
