package com.qfedu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.entity.Role;
import com.qfedu.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService rSer;
	
	@RequestMapping("/findRoleByPage")
	@ResponseBody
	public Map<String, Object> findRoleByPage(String page, String limit) {
		Map<String, Object> map = new HashMap<>();
		List<Role> list = new ArrayList<>();
		int count = 0;
		
		try {
			count = rSer.count();
			list = rSer.findRoleByPage(Integer.parseInt(page.trim()) , Integer.parseInt(limit.trim()));
			map.put("code", 0);
			map.put("data", list);
			map.put("count", count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("data", e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/findAllRole")
	@ResponseBody
	public Map<String, Object> findAllRole() {
		Map<String, Object> map = new HashMap<>();
		List<Role> list = new ArrayList<>();
		
		try {
			list = rSer.findAllRole();
			map.put("code", 0);
			map.put("data", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
	
}
