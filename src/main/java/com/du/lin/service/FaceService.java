package com.du.lin.service;



import com.du.lin.bean.Users;
import java.util.List;

public interface FaceService {

	public List<Users> selectAllUsers();
	
    public int save(Users user);
    
    public Users queryInfoByUsername(String username);

}
