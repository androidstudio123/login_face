package com.du.lin.service.impl;

import com.du.lin.bean.login;
import com.du.lin.dao.LoginMapper;
import com.du.lin.service.AdminLoginService;
import com.du.lin.utils.JqgridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private JqgridUtil jqgridUtil;
    @Override
    public String getAlllogin(int page, int count) {
        List<login> all=loginMapper.selectList(null);
        int toIndex=count * page;
        if(all.size()<toIndex){
            toIndex=all.size();
        }
        List<login> list=all.subList(count*(page-1),toIndex);
        return jqgridUtil.getJson(list,page+" ",all.size(),count);
    }
}
