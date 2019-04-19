package com.du.lin.service;


import com.du.lin.bean.commodity;

import java.util.List;

public interface CommodityService {
    List<commodity> findAll();
    List<commodity> findBycategory_id(int category_id);
    List<commodity> findBycategory_Id(int category_id);
    List<commodity> findByquailty(String quailty);
    List<commodity> Searchcommodity(String Search);
    List<commodity> findcommodityBylogin_id(int login_id);
    int findlogin_idBycommodity_id(int id);
    commodity save(commodity commodity);
    void deletecommByid(int comm_id);
}
