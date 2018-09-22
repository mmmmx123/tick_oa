package com.qfedu.dao;

import java.util.Map;

public interface UserRoleKeyDao {
    
	public void addUserRoleKey(Map<String, Object> map);

	public void deleteUserRoleKey(int uid);
}