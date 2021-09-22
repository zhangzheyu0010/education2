package com.baizhi.zzy.controller;


import com.baizhi.zzy.entity.Banner;
import com.baizhi.zzy.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("queryBanner")
    public List<Banner> queryBanner(){
        return bannerService.getQueryBannerAll();
    }


}
