package com.yjt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjt.entity.po.RolePermission;
import com.yjt.mapper.RolePermissionMapper;
import com.yjt.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
		implements RolePermissionService {

	@Override
	public List<RolePermission> findByRole(Integer roleId) {
		
		return baseMapper.selectList(new QueryWrapper<RolePermission>().eq("ROLE_ID", roleId));
	}

}
