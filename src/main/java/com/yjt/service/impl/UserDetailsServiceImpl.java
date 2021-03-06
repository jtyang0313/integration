package com.yjt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.yjt.entity.po.Permission;
import com.yjt.entity.po.User;
import com.yjt.entity.po.UserRole;
import com.yjt.mapper.PermissionMapper;
import com.yjt.mapper.RoleMapper;
import com.yjt.mapper.RolePermissionMapper;
import com.yjt.mapper.UserMapper;
import com.yjt.mapper.UserRoleMapper;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserRoleMapper uRoleMapper;

	@Autowired
	RolePermissionMapper rolePermissionMapper;

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	PermissionMapper permissionMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {

    	User user = userMapper.findByName(username);

		if (user == null) {
			throw new BadCredentialsException("用户名不存在");
		}

		List<UserRole> userRoles = uRoleMapper.selectByUserId(user.getId());
		List<Integer> roleIds = new ArrayList<>();
		if (userRoles != null && userRoles.size() > 0) {
			for (UserRole userRole : userRoles) {
				roleIds.add(userRole.getRoleId());
			}
		}
		List<Permission> permissionList = permissionMapper.getPerByRid(roleIds);
		user.setPermissions(permissionList);

		return user;
    }

}
