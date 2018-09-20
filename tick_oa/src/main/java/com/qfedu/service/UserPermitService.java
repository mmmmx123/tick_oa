package com.qfedu.service;


import java.util.List;
import java.util.Map;

import com.qfedu.vo.UserPermit;

public interface UserPermitService {

	public List<Map<String, Object>> findPermitByNo(String no);
	
	
}
