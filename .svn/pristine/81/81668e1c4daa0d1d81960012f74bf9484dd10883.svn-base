package com.yjt.entity.po;

import java.io.Serializable;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("tb_permission")
public class Permission implements Serializable {

	@TableId(type= IdType.AUTO)
	private String id;

    private String parentId;
    
    private String permissionName;

    private String permissionCode;

}