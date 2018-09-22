package com.qfedu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.dao.AuthorityDao;
import com.qfedu.entity.Authority;
import com.qfedu.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityDao aDao;
	
	@Override
	public List<Authority> findAuthByPage(int page, int limit) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		List<Authority> list = new ArrayList<>();
		int index = (page - 1) * limit;
		map.put("index", index);
		map.put("size", limit);
		
		try {
			list = aDao.findByIndexAndSize(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("查询失败");
		}
		
		return list;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		int count = 0;
		
		try {
			count = aDao.count();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public List<Authority> findAllAuth() {
		// TODO Auto-generated method stub
		List<Authority> list = new ArrayList<>();
		
		try {
			list = aDao.findAllAuth();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
