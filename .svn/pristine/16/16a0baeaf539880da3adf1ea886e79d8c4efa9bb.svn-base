package com.yjt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjt.entity.po.Permission;
import com.yjt.mapper.PermissionMapper;
import com.yjt.service.PermissionService;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<Permission> getPerByRid(List<Integer> roleIds) {

		return permissionMapper.getPerByRid(roleIds);
	}

}
