package com.yjt.entity.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

@Data
@TableName("tb_role")
public class Role extends Model<Role> {

	private static final long serialVersionUID = 1L;

	@TableId(type= IdType.AUTO)
	private Integer id;

    private String companyName;

    private String roleName;

    private String roleNote;

    private Date createTime;

    private Date updateTime;
    
    @TableField(exist = false)
    private String roleSign;

	@Override
	protected Serializable pkVal() {
		return null;
	}
    
}