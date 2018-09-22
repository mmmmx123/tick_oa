package com.qfedu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.dao.UserRoleDao;
import com.qfedu.service.UserRoleService;
import com.qfedu.vo.UserRole;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao urDao;
	
	@Override
	public List<UserRole> findRoleByPage(int page, int limit, String no, Integer flag) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		List<UserRole> list = new ArrayList<>();
		
		int index = (page - 1) * limit;
		
		if (no == null || no.trim().equals("")) {
			no = null;
		}

		map.put("index", index);
		map.put("size", limit);
		map.put("no", no);
		map.put("flag", flag);
		
		try {
			list = urDao.findByIndexAndSize(map);
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
			count = urDao.count();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public void deleteUserRoleById(int id) {
		// TODO Auto-generated method stub
		
		try {
			urDao.deleteUserRoleById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("删除失败");
		}
		
	}

}
