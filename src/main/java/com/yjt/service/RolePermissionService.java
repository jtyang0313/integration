package com.yjt.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjt.entity.po.RolePermission;

public interface RolePermissionService extends IService<RolePermission> {

	List<RolePermission> findByRole(Integer roleId);
}
