package com.yjt.entity.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("tb_company")
public class Company implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@TableId(type= IdType.AUTO)
    private Integer id;

    private String companyCode;

    private String companyName;

    private Integer type;

    private String contacts;

    private String telPhone;

    private String address;

    private Date createTime;

    private Date updateTime;
    
}