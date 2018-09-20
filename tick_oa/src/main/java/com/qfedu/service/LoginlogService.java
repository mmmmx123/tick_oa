package com.qfedu.service;

import java.util.List;

import com.qfedu.entity.Loginlog;

public interface LoginlogService {

	public List<Loginlog> findLoginlogByPage(int page, int limit);
	
	public void addLoginlog(Loginlog ll);
	
	public int count();
}
