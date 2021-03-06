package com.yjt.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjt.entity.po.Role;
import com.yjt.entity.po.RolePermission;
import com.yjt.entity.po.UserRole;
import com.yjt.mapper.RoleMapper;
import com.yjt.mapper.RolePermissionMapper;
import com.yjt.mapper.UserRoleMapper;
import com.yjt.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	@Transactional
	public void add(Role role, String permissionIds) {

		role.setCreateTime(new Date());
		role.setUpdateTime(new Date());
		roleMapper.insert(role);

		if (StringUtils.isNotBlank(permissionIds)) {
			String[] permIds = permissionIds.split(",");
			for (String permId : permIds) {
				RolePermission rolePerm = new RolePermission();
				rolePerm.setRoleId(role.getId());
				rolePerm.setPermissionId(permId);
				rolePermissionMapper.insert(rolePerm);
			}
		}

	}

	@Override
	@Transactional
	public void edit(Role role, String permissionIds) {
		// 更新角色
		roleMapper.updateById(role);

		// 删除原来的角色权限关系
		QueryWrapper<RolePermission> ew = new QueryWrapper<RolePermission>();
		ew.eq("role_id", role.getId());
		rolePermissionMapper.delete(ew);

		// 重新插入角色权限关系
		if (StringUtils.isNotBlank(permissionIds)) {
			String[] permIds = permissionIds.split(",");
			for (String permId : permIds) {
				RolePermission rolePerm = new RolePermission();
				rolePerm.setRoleId(role.getId());
				rolePerm.setPermissionId(permId);
				rolePermissionMapper.insert(rolePerm);
			}
		}
	}

	@Override
	@Transactional
	public String delete(Integer roleId) {

		int num = userRoleMapper.selectCount(new QueryWrapper<UserRole>().eq("role_id", roleId));

		if (num > 0) {
			return "error";
		} else {
			roleMapper.deleteById(roleId);
			QueryWrapper<RolePermission> ew = new QueryWrapper<RolePermission>();
			ew.eq("role_id", roleId);
			rolePermissionMapper.delete(ew);
			return "success";
		}
	}

	@Override
	public Page<Role> selectByPage(Page<Role> page, Role role) {

		return roleMapper.selectByPage(page, role);
	}

}
