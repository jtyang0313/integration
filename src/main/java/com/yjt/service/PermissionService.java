package com.yjt.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yjt.entity.po.Permission;

public interface PermissionService extends IService<Permission> {
	
	/** 
	* @Title: getPerByRid 
	* @Description: 根据当前登录用户角色获取权限
	* @param @param roleIds
	* @param @return    设定文件 
	* @return List<Permission>    返回类型 
	* @throws 
	*/ 
	List<Permission>getPerByRid(List<Integer> roleIds);

}
