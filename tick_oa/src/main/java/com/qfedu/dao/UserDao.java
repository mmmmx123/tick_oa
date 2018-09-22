package com.qfedu.dao;

import com.qfedu.entity.User;
import com.qfedu.vo.UserPermit;

public interface UserDao {

	public User findByNo(String no);
	
	public void deleteUserById(int id);
	
}