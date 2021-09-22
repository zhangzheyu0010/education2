package com.baizhi.zzy.controller;


import com.baizhi.zzy.entity.Nav;
import com.baizhi.zzy.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("nav")
public class NavController {
    @Autowired
    private NavService navService;

    @GetMapping("queryNav")
    public List<Nav> queryNav(){
        List<Nav> list = navService.getQueryNavAll();
        return list;
    }



}
