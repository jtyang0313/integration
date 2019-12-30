package com.yjt.entity.po;

import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("tb_bill_detail")
public class BillDetail {

	@TableId(type= IdType.AUTO)
	private Integer id;

    private String billCode;

    private String boxCode;

    private String costCode;

    private String costName;

	@TableField(exist=false)
    private String amountStr;

	private Long amount;
	
    private String currency;

    private Date createTime;

}
