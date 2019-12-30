package com.yjt.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.entity.po.User;

public interface UserMapper extends BaseMapper<User> {

	User findByName(String userName);
	
	Page<User> selectByPage(Page<User> page, @Param("user")User user);

	User findById(Integer id);
	
}
