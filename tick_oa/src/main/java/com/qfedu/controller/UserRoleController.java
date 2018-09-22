package com.qfedu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.service.UserRoleService;
import com.qfedu.vo.UserRole;

@Controller
public class UserRoleController {

	@Autowired
	private UserRoleService urSer;
	
	@RequestMapping("/findUserRoleByPage")
	@ResponseBody
	public Map<String, Object> findUserRoleByPage(String page, String limit, String no, Integer flag) {
		Map<String, Object> map = new HashMap<>();
		List<UserRole> list = new ArrayList<>();
		int count = 0;
		
		try {
			count = urSer.count();
			list = urSer.findRoleByPage(Integer.parseInt(page.trim()), Integer.parseInt(limit.trim()),
					no, flag);
			map.put("code", 0);
			map.put("data", list);
			map.put("count", count);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("data", e.getMessage());
		}
		
		return map;
	}
	
}
