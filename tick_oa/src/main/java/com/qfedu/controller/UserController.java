package com.qfedu.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.entity.Loginlog;
import com.qfedu.service.LoginlogService;
import com.qfedu.service.UserPermitService;
import com.qfedu.service.UserRoleService;
import com.qfedu.service.UserService;
import com.qfedu.utils.AddressUtils;
import com.qfedu.utils.IpGet;

@Controller
public class UserController {

	@Autowired
	private UserPermitService uSer;
	
	@Autowired
	private LoginlogService llSer;
	
	@Autowired
	private UserService usSer;
	
	@Autowired
	private UserRoleService urSer;
	
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(String no, String password, HttpServletRequest request) {
		SimpleHash sh = new SimpleHash("MD5", password, null, 1);
		UsernamePasswordToken token = new UsernamePasswordToken(no, sh.toString().trim());
		Subject subject = SecurityUtils.getSubject();
		Map<String, Object> map = new HashMap<>();
		
		Loginlog ll = new Loginlog();
		
		try {
			subject.login(token);
			String ip = IpGet.getIpAddr(request);
			String addresses = AddressUtils.getAddresses("ip=" + ip , "UTF-8");
			ll.setCreatetime(new Date());
			ll.setIp(ip);
			ll.setLocation(addresses);
			ll.setNo(no);
			llSer.addLoginlog(ll);
			map.put("code", 0);
			map.put("msg", "");
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", e.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Map<String, Object> deleteUserById(String id) {
		Map<String, Object> map = new HashMap<>();
		
		try {
			usSer.deleteUserById(Integer.parseInt(id.trim()));
			urSer.deleteUserRoleById(Integer.parseInt(id.trim()));
			map.put("code", 0);
			map.put("msg", null);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", e.getMessage());
		}
		
		return map;
	}
}
