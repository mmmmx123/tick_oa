package com.qfedu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.service.UserRoleKeyService;

@Controller
public class UserRoleKeyController {

	@Autowired
	private UserRoleKeyService urkSer;
	
	@RequestMapping("/userroleedit")
	@ResponseBody
	public Map<String, Object> UserRoleEdit(String id, String rids) {
		Map<String, Object> ret = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		int uid = Integer.parseInt(id.trim());
		map.put("uid", uid);
		map.put("rids", rids);
		
		try {
			urkSer.updateUserRoleKey(map);
			ret.put("code", 0);
			ret.put("msg", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret.put("code", 1);
			ret.put("msg", e.getMessage());
		}
		
		return ret;
	}
	
}
