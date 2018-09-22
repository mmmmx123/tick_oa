package com.qfedu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.dao.RoleDao;
import com.qfedu.entity.Role;
import com.qfedu.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao rDao;
	
	@Override
	public List<Role> findRoleByPage(int page, int limit) {
		// TODO Auto-generated method stub
		List<Role> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		int index = (page - 1) * limit;
		
		map.put("index", index);
		map.put("size", limit);
		
		try {
			list = rDao.findByIndexAndSize(map);
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
			count = rDao.count();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		List<Role> list = new ArrayList<>();
		
		try {
			list = rDao.findAllRole();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
