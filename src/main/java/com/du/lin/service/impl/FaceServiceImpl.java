package com.du.lin.service.impl;

import com.du.lin.bean.Users;
import com.du.lin.dao.FaceDao;
import com.du.lin.service.FaceService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class FaceServiceImpl implements FaceService {
	@Resource
	private FaceDao facedao;


	public List<Users> selectAllUsers() {
		return facedao.selectAllUsers();
	}


	@Override
	public int save(Users user) {
		 return facedao.save(user);
	}


	@Override
	public Users queryInfoByUsername(String username) {
		// TODO Auto-generated method stub
		 return facedao.queryInfoByUsername(username);
	}

}
