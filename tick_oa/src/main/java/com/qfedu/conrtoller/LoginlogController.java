package com.qfedu.conrtoller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.entity.Loginlog;
import com.qfedu.service.LoginlogService;
import com.qfedu.utils.AddressUtils;
import com.qfedu.utils.IpGet;

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

	@RequestMapping("/addloginlog")
	@ResponseBody
	public Map<String, Object> addLoginlog(HttpServletRequest request) {
		Loginlog ll = new Loginlog();
		String addresses= null;
		Map<String, Object> map = new HashMap<>();
		String no = (String) SecurityUtils.getSubject().getPrincipal();
		String ip = IpGet.getIpAddr(request);
		try {
			addresses = AddressUtils.getAddresses("ip=" + ip , "UTF-8");
			ll.setCreatetime(new Date());
			ll.setIp(ip);
			ll.setLocation(addresses);
			ll.setNo(no);
			llSer.addLoginlog(ll);
			map.put("code", 0);
			map.put("data", ll);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("data", e.getMessage());
		}

		return map;
	}

}
