package com.yjt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjt.entity.po.Role;

public interface RoleService extends IService<Role> {

	void add(Role role, String permissionIds);

	void edit(Role role, String permissionIds);

	String delete(Integer roleId);

	Page<Role> selectByPage(Page<Role> page, Role role);
	
}
