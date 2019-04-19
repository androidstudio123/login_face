package com.du.lin.dao;

import com.du.lin.bean.commimage;

import java.util.List;

public interface CommoimageDao {
    List<commimage> findBycommodityid(int commodity_id);
}
