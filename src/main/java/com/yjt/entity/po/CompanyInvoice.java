package com.yjt.entity.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
@Getter
@Setter
@TableName("tb_company_invoice")
public class CompanyInvoice extends Model<CompanyInvoice>{
    
	private static final long serialVersionUID = 1L;
	
	@TableId(type= IdType.AUTO)
	private Integer id;

    private String companyCode;

    private String companyRise;

    private String dutyParagraph;

    private String address;

    private String bank;

    private String bankAccount;

    private String telephone;

    private Integer state;
    
    private String operateName;
    
    private String remark;

    private Date createTime;

    private Date updateTime;

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.id;
	}

}