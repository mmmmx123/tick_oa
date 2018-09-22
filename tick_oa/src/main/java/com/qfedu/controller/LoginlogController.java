package com.qfedu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.entity.Loginlog;
import com.qfedu.service.LoginlogService;

@Controller
public class LoginlogController {

	@Autowired
	private LoginlogService llSer;

	@RequestMapping("/loginlog")
	@ResponseBody
	public Map<String, Object> loginlog(String page, String limit) {
		Map<String, Object> map = new HashMap<>();
		List<Loginlog> list = new ArrayList<>();
		int count = 0;

		try {
			count = llSer.count();
			list = llSer.findLoginlogByPage(Integer.parseInt(page.trim()), Integer.parseInt(limit.trim()));
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

	
	@RequestMapping("/loginnum")
	@ResponseBody
	public Map<String, Object> loginNum() {
		Map<String, Object> map = new HashMap<>();
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		int size = 0;
	
		try {
			size = principals.asList().size();
			map.put("code", 0);
			map.put("msg", size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
}
