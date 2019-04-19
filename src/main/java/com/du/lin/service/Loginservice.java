package com.du.lin.service;

import com.du.lin.bean.login;

import java.util.List;

public interface Loginservice {
    void save(login list) ;
    List<login> findAll();
    List<login> findidByusername(String username);
    login findAllByid(int id);
}
