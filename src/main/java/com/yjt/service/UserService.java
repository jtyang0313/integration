package com.yjt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjt.entity.po.User;

public interface UserService extends IService<User>{
	
	User findByName(String userName);
	 
	boolean insert(User user);

	boolean updateUser(User user);
	
	boolean deleteUser(Integer id);
	
	Page<User> selectByPage(Page<User> page, User user);
	
	User selectById(Integer id);
}
