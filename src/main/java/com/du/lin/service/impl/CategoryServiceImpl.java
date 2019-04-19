package com.du.lin.service.impl;

import com.du.lin.bean.category;
import com.du.lin.dao.CategoryDao;
import com.du.lin.service.Categoryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CategoryServiceImpl implements Categoryservice {
    @Autowired
    CategoryDao categoryDao;
    @Override
    public List<category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Map<Integer, String> listByMap() {
        List<category> categories = categoryDao.findAll();
        Map<Integer, String> categoriesMap = new HashMap<>();
        for (category category : categories) {
            categoriesMap.put(category.getId(),category.getName());
        }
        return categoriesMap;
    }

    @Override
    public List<category> findvalueByid(int category_id) {
        List<category> value = categoryDao.findvalueByid(category_id);
        return value;
    }

    @Override
    public category findidByname(String name) {
        category list=categoryDao.findidByname(name);
        return list;
    }


}
