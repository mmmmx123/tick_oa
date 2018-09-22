package com.qfedu.service;

import java.util.List;

import com.qfedu.vo.UserRole;

public interface UserRoleService {

	public List<UserRole> findRoleByPage(int page, int limit, String no, Integer flag);
	
	public int count();
	
	public void deleteUserRoleById(int id);
}
