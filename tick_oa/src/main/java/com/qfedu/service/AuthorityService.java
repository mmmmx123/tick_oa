package com.qfedu.service;

import java.util.List;

import com.qfedu.entity.Authority;

public interface AuthorityService {

	public List<Authority> findAuthByPage(int page, int limit);
	
	public int count();
	
	public List<Authority> findAllAuth();
}
