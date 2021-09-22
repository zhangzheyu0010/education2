package com.baizhi.zzy.service;

import com.baizhi.zzy.dao.NavDao;
import com.baizhi.zzy.entity.Nav;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NavServiceImpl implements NavService {
    @Autowired
    private NavDao navDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Nav> getQueryNavAll() {
        return navDao.queryNavAll();
    }
}
