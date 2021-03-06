package com.yjt.entity.po;

import java.util.Date;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("tb_company_file")
public class CompanyFile {
	
	@TableId(type= IdType.AUTO)
    private Integer id;

    private String companyCode;

    private Integer userId;
    
    private String fileName;
    
    private String filePath;
    
    private Date uploadTime;

    private String memo;
    
    @TableField(exist = false)
    private String userName;
    
}