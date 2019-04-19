package com.du.lin.service;

import com.du.lin.bean.commimage;

import java.util.List;

public interface CommimageService {
    List<commimage> findBycommodityid(int commodity_id);
}
