package com.qfedu.service;

import java.util.List;

import com.qfedu.entity.Role;

public interface RoleService {

	public List<Role> findRoleByPage(int page, int limit);
	
	public int count();
	
	public List<Role> findAllRole();
}
