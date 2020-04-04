package com.mfq.edu.client;

import com.mfq.edu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "service-vod", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    //定义调用方法所在的路径
    @DeleteMapping("/vod/remove/{videoId}")
    R removeVideo(@PathVariable("videoId") String videoId);

    // 批量删除视频
    @DeleteMapping("/remove-batch")
    R removeBath(@RequestParam List<String> ids);
}
