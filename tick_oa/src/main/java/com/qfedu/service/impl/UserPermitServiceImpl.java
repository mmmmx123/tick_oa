package com.qfedu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qfedu.dao.UserPermitDao;
import com.qfedu.service.UserPermitService;
import com.qfedu.vo.UserPermit;

@Service
public class UserPermitServiceImpl implements UserPermitService {

	@Autowired
	private UserPermitDao upDao;

	@Override
	public List<Map<String, Object>> findPermitByNo(String no) {
		// TODO Auto-generated method stub
		List<UserPermit> uplist = new ArrayList<>();
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			uplist = upDao.findPermitByNo(no);
			int i = 0;
			int count = 0;
			for (UserPermit u : uplist) {
				Integer id = u.getId();
				if (i == 0 || id != uplist.get(i - 1).getId()) {
					List<Map<String, Object>> slist = new ArrayList<>();
					Map<String, Object> map = new HashMap<>();
					map.put("id", u.getId());
					map.put("no", u.getNo());
					map.put("title", u.getTitle());
					map.put("aicon", u.getAicon());
					map.put("aurl", u.getAurl());
					Map<String, Object> map1 = new HashMap<>();
					map1.put("sid", u.getSid());
					map1.put("stitle", u.getStitle());
					map1.put("saicon", u.getSaicon());
					map1.put("saurl", u.getSaurl());
					slist.add(map1);
					map.put("son", slist);
					list.add(map);
					i++;
					count++;
				} else {
					Map<String, Object> map1 = new HashMap<>();
					map1.put("sid", u.getSid());
					map1.put("stitle", u.getStitle());
					map1.put("saicon", u.getSaicon());
					map1.put("saurl", u.getSaurl());
					List<Map<String, Object>> object = (List<Map<String, Object>>) list.get(count - 1).get("son");
					object.add(map1);
					i++;
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}
