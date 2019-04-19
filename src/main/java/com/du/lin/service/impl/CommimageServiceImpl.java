package com.du.lin.service.impl;


import com.du.lin.bean.commimage;
import com.du.lin.dao.CommoimageDao;
import com.du.lin.service.CommimageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommimageServiceImpl implements CommimageService {
    @Autowired
    CommoimageDao commoimageDao;
    @Override
    public List<commimage> findBycommodityid(int commodity_id) {
        List<commimage> image=commoimageDao.findBycommodityid(commodity_id);
        return image;
    }
}
