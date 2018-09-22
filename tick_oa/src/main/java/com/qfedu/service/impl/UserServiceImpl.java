package com.qfedu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.service.UserRoleService;
import com.qfedu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserService uSer;
	
	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		try {
			uSer.deleteUserById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("删除失败");
		}

	}

}
