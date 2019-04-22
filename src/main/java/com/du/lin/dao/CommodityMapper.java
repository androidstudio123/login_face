package com.du.lin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.du.lin.bean.commodity;

import java.util.List;

public interface CommodityMapper {
    List<commodity> findAll();
}
