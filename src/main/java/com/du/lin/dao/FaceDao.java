package com.du.lin.dao;

import com.du.lin.bean.Users;

import java.util.List;


public interface FaceDao {

	public List<Users> selectAllUsers();

	public int save(Users users);
	
	 public Users queryInfoByUsername(String username);

}
