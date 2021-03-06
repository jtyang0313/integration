package com.yjt.entity.po;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("tb_user_role")
public class UserRole {
    
	@TableId(type= IdType.AUTO)
	private Integer id;

    private Integer userId;

    private Integer roleId;

}