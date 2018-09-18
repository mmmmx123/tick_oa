package com.qfedu.dao;

import com.qfedu.entity.User;

public interface UserDao {

	public User findByNo(String no);
}