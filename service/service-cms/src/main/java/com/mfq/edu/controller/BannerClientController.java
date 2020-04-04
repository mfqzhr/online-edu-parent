package com.mfq.edu.controller;


import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.CrmBanner;
import com.mfq.edu.service.CrmBannerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author 穆繁强
 * @since 2020-04-03
 */
@RestController
@RequestMapping("crm/banner/client")
@CrossOrigin
@Api("banner前台显示")
public class BannerClientController {
    @Autowired
    private CrmBannerService bannerService;

    //查询banner
    @Cacheable(value = "banner", key = "'selectIndexList'")
    @GetMapping()
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.list(null);
        return R.ok().data("list", list);
    }
}

