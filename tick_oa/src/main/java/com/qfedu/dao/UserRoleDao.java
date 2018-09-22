package com.qfedu.dao;

import java.util.List;
import java.util.Map;

import com.qfedu.vo.UserRole;

public interface UserRoleDao {

	public UserRole findRoleByNo(String no);
	
	public List<UserRole> findByIndexAndSize(Map<String, Object> map);
	
	public int count();
	
	public void deleteUserRoleById(int id);
	
	
}
