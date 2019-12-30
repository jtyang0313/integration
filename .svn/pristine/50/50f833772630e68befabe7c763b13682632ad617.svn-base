package com.yjt.entity.vo;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import cn.afterturn.easypoi.excel.annotation.Excel;

@Data
public class BillExcel extends ExcelErroMsg{

    @Excel(name="账单编号")
    @NotEmpty(message = "账单编号不能为空")
    private String billCode;
    
    @Excel(name="账单日期")
    @NotNull(message = "账单日期不能为空")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date billDate;

    @Excel(name="账单金额")
    @NotNull(message = "账单金额不能为空")
    private String billAmountStr;

    @Excel(name="账单币种")
    @NotEmpty(message = "账单币种不能为空")
    private String billCurrency;

    @Excel(name="备注")
    @NotEmpty(message = "备注不能为空")
    private String billMemo;
    
    @Excel(name="账单企业")
    @NotEmpty(message = "账单企业不能为空")
    private String companyName;
}
