package com.du.lin.service.impl;

import com.du.lin.bean.category;
import com.du.lin.dao.CategoryMapper;
import com.du.lin.service.AdminCategoryService;
import com.du.lin.utils.JqgridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService{
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private JqgridUtil jqgridUtil;

    @Override
    public String getAllcategory(int page, int count) {
        List<category> all =categoryMapper.findAll();
        int toIndex = count * page;
        if (all.size() < toIndex) {
            toIndex = all.size();
        }
        List<category> list = all.subList(count * (page - 1), toIndex);
        return jqgridUtil.getJson(list, page + "", all.size() , count);
    }
}
