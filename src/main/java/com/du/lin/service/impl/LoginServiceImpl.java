package com.du.lin.service.impl;
import com.du.lin.bean.login;
import com.du.lin.dao.LoginDao;
import com.du.lin.service.Loginservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginServiceImpl implements Loginservice {
    @Autowired
    LoginDao loginDao;
    @Override
    public void save(login list) {
        loginDao.save(list);
    }

    @Override
    public List<login> findAll() {
        return loginDao.findAll();
    }

    @Override
    public List<login> findidByusername(String username) {
        return loginDao.findidByusername(username);
    }

    @Override
    public login findAllByid(int id) {
        return loginDao.findAllByid(id);
    }

}
