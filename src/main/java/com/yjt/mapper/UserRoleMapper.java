package com.yjt.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjt.entity.po.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole> {

	List<UserRole> selectByUserId(Integer userId);

	int batchInsert(List<UserRole> list);

	int deleteByUserId(Integer id);
	
}
