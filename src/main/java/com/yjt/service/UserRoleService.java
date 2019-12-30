package com.yjt.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjt.entity.po.UserRole;

public interface UserRoleService extends IService<UserRole> {

	List<UserRole> selectByUserId(Integer userId);
	
}
