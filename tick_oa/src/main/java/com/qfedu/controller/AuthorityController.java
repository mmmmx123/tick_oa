package com.qfedu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.entity.Authority;
import com.qfedu.service.AuthorityService;

@Controller
public class AuthorityController {

	@Autowired
	private AuthorityService aSer;

	@RequestMapping("/authoritylist")
	@ResponseBody
	public Map<String, Object> authorityList(Integer page, Integer limit) {
		Map<String, Object> ret = new HashMap<>();
		List<Authority> list = null;
		int count = 0;

		try {
			count = aSer.count();
			list = aSer.findAuthByPage(page, limit);
			ret.put("code", 0);
			ret.put("data", list);
			ret.put("count", count);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret.put("code", 1);
			ret.put("msg", e.getMessage());
		}

		return ret;
	}
	
	@RequestMapping("/findAllAuth")
	@ResponseBody
	public Map<String, Object> findAllAuth() {
		Map<String, Object> map = new HashMap<>();
		List<Authority> list = null;

		try {
			list = aSer.findAllAuth();
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
