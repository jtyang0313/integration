package com.yjt.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.entity.po.Role;

public interface RoleMapper extends BaseMapper<Role> {

	Page<Role> selectByPage(Page<Role> page, @Param("role")Role role);
	
}
