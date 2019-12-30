package com.yjt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjt.entity.po.UserRole;
import com.yjt.mapper.UserRoleMapper;
import com.yjt.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

	@Autowired

	private UserRoleMapper uMapper;

	@Override
	public List<UserRole> selectByUserId(Integer userId) {

		return uMapper.selectByUserId(userId);
	}

}
