package com.yjt.entity.po;


import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("tb_role_permission")
public class RolePermission {
    
	@TableId(type= IdType.AUTO)
	private Integer id;

    private Integer roleId;

    private String permissionId;

}