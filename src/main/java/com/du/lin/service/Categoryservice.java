package com.du.lin.service;

import com.du.lin.bean.category;
import java.util.List;
import java.util.Map;

public interface Categoryservice {
    List<category> findAll();
    Map<Integer,String> listByMap();
    List<category> findvalueByid(int category_id);
    category findidByname(String name);
}
