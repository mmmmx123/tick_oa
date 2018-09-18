package com.qfedu.conrtoller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

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
}
