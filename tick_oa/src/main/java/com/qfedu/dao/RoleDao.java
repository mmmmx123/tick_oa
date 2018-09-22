package com.qfedu.dao;

import java.util.List;
import java.util.Map;

import com.qfedu.entity.Role;

public interface RoleDao {
    
	public List<Role> findByIndexAndSize(Map<String, Object> map);
	
	public int count();
	
	public List<Role> findAllRole();
}