package com.du.lin.service.impl;

import com.du.lin.bean.commodity;
import com.du.lin.bean.login;
import com.du.lin.dao.CommodityMapper;
import com.du.lin.dao.LoginMapper;
import com.du.lin.service.AdminCommodityService;
import com.du.lin.utils.JqgridUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCommodityServiceImpl implements AdminCommodityService {
@Autowired
    CommodityMapper commodityMapper;
    @Autowired
    private JqgridUtil jqgridUtil;
    @Autowired
    private LoginMapper loginMapper;
    public String getAllcommodity(int page , int count) {
        //List<commodity> list=commodityMapper.selectList(null);
        List<commodity> all =commodityMapper.findAll();
        int toIndex = count * page;
        if (all.size() < toIndex) {
            toIndex = all.size();
        }
        List<commodity> list = all.subList(count * (page - 1), toIndex);
        return jqgridUtil.getJson(list, page + "", all.size() , count);
    }

    @Override
    public String commodityListForLoginAdd() {
        List<login> list = loginMapper.selectList(null);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).getId() + ":" + list.get(i).getUsername() + ";");
        }
        return sb.substring(0, sb.length()-1);
    }

}
