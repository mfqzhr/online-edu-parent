package com.mfq.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mfq.edu.commonutils.R;
import com.mfq.edu.entity.CrmBanner;
import com.mfq.edu.service.CrmBannerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("crm/banner/admin")
@CrossOrigin
@Api(tags = "banner后台管理")
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    //1 分页查询banner
    @GetMapping("/{page}/{limit}")
    public R pageBanner(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        Page<CrmBanner> banner = new Page<>(page, limit);
        IPage<CrmBanner> res = bannerService.page(banner, null);
        List<CrmBanner> records = res.getRecords();
        long total = res.getTotal();
        return R.ok().data("list", records).data("total", total);
    }

    //2 添加banner
    @PostMapping("")
    public R addBanner(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return R.ok();
    }

    //3 根据ID删除banner
    @DeleteMapping("/{id}")
    public R deleteBanner(@PathVariable("id") String id) {
        bannerService.removeById(id);
        return R.ok();
    }

    //4 更新banner
    @PutMapping("/{id}")
    public R updateBanner(@PathVariable("id") String id, @RequestBody CrmBanner banner) {
        banner.setId(id);
        bannerService.updateById(banner);
        return R.ok();
    }
}

