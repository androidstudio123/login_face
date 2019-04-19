package com.du.lin.controller;

import com.du.lin.service.Categoryservice;
import com.du.lin.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {
    @Autowired
    private Categoryservice categoryservice;
    @Autowired
    private CommodityService commodityService;
    @RequestMapping("gocommodity")
    public String commodity(){
        return "commodity";
    }
}
