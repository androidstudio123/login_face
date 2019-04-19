package com.du.lin.controller;

import com.du.lin.bean.commodity;
import com.du.lin.service.CommodityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    CommodityService commodityService;
    @GetMapping("Searchcommodity")
    public String Searchcommodity(Model m, @RequestParam String Search,@RequestParam(value = "start",defaultValue = "0") int start,@RequestParam(value = "size",defaultValue="8") int size){
        PageHelper.startPage(start,size,"id desc");
        List<commodity> list=commodityService.Searchcommodity('%'+Search+'%');
        PageInfo<commodity> page=new PageInfo<>(list);
        m.addAttribute("page", page);
        m.addAttribute("jieguo","搜索结果");
        m.addAttribute("search",Search);
        return "search";
    }
}
