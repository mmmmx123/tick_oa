package com.qfedu.dao;

import java.util.List;
import java.util.Map;

import com.qfedu.entity.Loginlog;

public interface LoginlogDao {
  
	public int count();

	public List<Loginlog> findByIndexAndSize(Map<String, Object> map);
	
	public void addLoginlog(Loginlog ll);
}