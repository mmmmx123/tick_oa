package com.qfedu.conrtoller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.service.UserPermitService;
import com.qfedu.vo.UserPermit;

@Controller
public class UserController {

	@Autowired
	private UserPermitService uSer;
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(String no, String password) {
		SimpleHash sh = new SimpleHash("MD5", password, null, 1);
		UsernamePasswordToken token = new UsernamePasswordToken(no, sh.toString().trim());
		Subject subject = SecurityUtils.getSubject();
		Map<String, Object> map = new HashMap<>();
		
		try {
			subject.login(token);
			map.put("code", 0);
			map.put("msg", "");
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
	
	@RequestMapping("/usermenu")
	@ResponseBody
	public Map<String, Object> userMenu() {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		String no = (String) SecurityUtils.getSubject().getPrincipal();
		try {
			list = uSer.findPermitByNo(no);
			map.put("code", 0);
			map.put("msg", list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
}
