package com.du.lin.dao;

import com.du.lin.bean.category;

import java.util.List;

public interface CategoryDao {
//    String a = "adfs";
    //接口中的方法默認識是public，所有接口中不能有實現
    List<category> findAll();
    List<category> findvalueByid(int category_id);
    category findidByname(String name);
}
