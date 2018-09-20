package com.qfedu.dao;

import java.util.List;

import com.qfedu.vo.UserPermit;

public interface UserPermitDao {

	public List<UserPermit> findPermitByNo(String no);
}
