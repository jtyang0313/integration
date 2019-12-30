package com.yjt.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjt.entity.po.Permission;

public interface PermissionMapper extends BaseMapper<Permission> {

	List<Permission> getPerByRid(List<Integer> roleIds);
	
}
