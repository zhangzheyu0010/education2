package com.baizhi.zzy.service;

import com.baizhi.zzy.dao.BannerDao;
import com.baizhi.zzy.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Banner> getQueryBannerAll() {
        return bannerDao.queryBannerAll();
    }
}
