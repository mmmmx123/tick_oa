package com.qfedu.dao;

import java.util.List;
import java.util.Map;

import com.qfedu.entity.Authority;

public interface AuthorityDao {
    
	public List<Authority> findByIndexAndSize(Map<String, Object>  map);
	
	public int count();
	
	public List<Authority> findAllAuth();
}