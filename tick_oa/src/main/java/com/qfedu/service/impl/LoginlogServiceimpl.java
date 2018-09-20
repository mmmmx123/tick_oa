package com.qfedu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.dao.LoginlogDao;
import com.qfedu.entity.Loginlog;
import com.qfedu.service.LoginlogService;

@Service
public class LoginlogServiceimpl implements LoginlogService {

	@Autowired
	private LoginlogDao lDao;
	
	@Override
	public void addLoginlog(Loginlog ll) {
		// TODO Auto-generated method stub
		try {
			lDao.addLoginlog(ll);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public List<Loginlog> findLoginlogByPage(int page,int limit) {
		// TODO Auto-generated method stub
		int index = (page - 1) * limit; 
		Map<String, Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", limit);
		List<Loginlog> list = new ArrayList<>();
		
		try {
			list = lDao.findByIndexAndSize(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
		
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		int count = 0;
		
		try {
			count = lDao.count();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

}
