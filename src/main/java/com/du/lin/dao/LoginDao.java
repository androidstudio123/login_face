package com.du.lin.dao;


import com.du.lin.bean.login;
import java.util.List;

public interface LoginDao {
    //gggg
    List<login> findAll();
     void save(login list);
    List<login>  findidByusername(String username);
   login findAllByid(int id);
}
