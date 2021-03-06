package com.yjt.entity.po;

import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("tb_bill")
public class Bill {
	
	@TableId(type= IdType.AUTO)
	private Integer id;

    private String companyCode;
    
    private String billCode;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date billDate;

    private Long billAmount;

    private String billCurrency;

    private String billMemo;
    
    private Integer state;
    
    private Date createTime;
    
    @TableField(exist=false)
    private String companyName;
    @TableField(exist=false)
    private String billAmountStr;
    
}
