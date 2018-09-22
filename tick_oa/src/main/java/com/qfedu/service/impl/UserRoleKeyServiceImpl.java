package com.qfedu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.dao.UserRoleKeyDao;
import com.qfedu.service.UserRoleKeyService;

@Service
public class UserRoleKeyServiceImpl implements UserRoleKeyService {

	@Autowired
	private UserRoleKeyDao urkDao;
	
	@Override
	public void updateUserRoleKey(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> sql = new HashMap<>();
		int uid = (int) map.get("uid");
		String rids = (String) map.get("rids");
		
		sql.put("uid", uid);
		
		try {
			urkDao.deleteUserRoleKey(uid);
			String[] split = rids.trim().split(",");
			for (String s : split) {
				sql.put("rid", Integer.parseInt(s.trim()));
				urkDao.addUserRoleKey(sql);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

	}

}
