package com.qfedu.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.qfedu.dao.UserDao;
import com.qfedu.dao.UserPermitDao;
import com.qfedu.dao.UserRoleDao;
import com.qfedu.entity.User;
import com.qfedu.vo.UserPermit;
import com.qfedu.vo.UserRole;

public class MyRealm extends AuthorizingRealm{

	@Autowired
	private UserRoleDao urDao;
	
	@Autowired
	private UserPermitDao pDao;
	
	@Autowired
	private UserDao uDao;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		Set<String> roleSet = new HashSet<>();
		Set<String> permitSet = new HashSet<>();
	
		String no = (String) principals.getPrimaryPrincipal();
		UserRole userRole = urDao.findRoleByNo(no);
		String role = userRole.getInfo();
		roleSet.add(role);
		
		List<UserPermit> permit = pDao.findPermitByNo(no);
		for (UserPermit u : permit) {
			permitSet.add(u.getStitle());
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roleSet);
		info.setStringPermissions(permitSet);
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String no = (String) token.getPrincipal();
		
		User user = uDao.findByNo(no);
		
		if (user == null) {
			throw new UnknownAccountException();
		}
		
		String password = user.getPassword();
		
		SimpleAuthenticationInfo infos = new SimpleAuthenticationInfo(no, password,this.getName());
		
		return infos;
	}

}
