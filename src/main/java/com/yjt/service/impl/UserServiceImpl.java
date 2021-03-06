package com.yjt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjt.entity.po.User;
import com.yjt.entity.po.UserRole;
import com.yjt.mapper.UserMapper;
import com.yjt.mapper.UserRoleMapper;
import com.yjt.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserRoleMapper userRoleMapper;

	@Autowired
	UserService userService;

	@Override
	public User findByName(String userName) {
		return userMapper.findByName(userName);
	}

	@Override
	public User selectById(Integer id) {

		User user = userMapper.findById(id);
		return user;
	}
	
	@Transactional
	@Override
	public boolean insert(User user) {
		
		try {
			userMapper.insert(user);
			List<UserRole> list = new ArrayList<>();
			List<Integer> roleIds = user.getRoleIds();
			for (Integer id : roleIds) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(id);
				userRole.setUserId(user.getId());
				list.add(userRole);
			}
			if (list.size() > 0) {
				userRoleMapper.batchInsert(list);
			}
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Transactional
	@Override
	public boolean updateUser(User user) {

		userMapper.updateById(user);
		Integer userId = user.getId();
		List<Integer> roleIds = user.getRoleIds();
		userRoleMapper.deleteByUserId(userId);
		List<UserRole> list = new ArrayList<>();
		for (Integer roleId : roleIds) {
			UserRole ur = new UserRole();
			ur.setUserId(userId);
			ur.setRoleId(roleId);
			list.add(ur);
		}
		if (list.size() > 0) {
			userRoleMapper.batchInsert(list);
		}
		return true;
	}

	@Transactional
	@Override
	public boolean deleteUser(Integer id) {

		userMapper.deleteById(id);
		userRoleMapper.deleteByUserId(id);
		return true;
	}

	@Override
	public Page<User> selectByPage(Page<User> page, User user) {

		return baseMapper.selectByPage(page, user);
	}

}
