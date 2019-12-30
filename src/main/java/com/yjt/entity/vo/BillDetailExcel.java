package com.yjt.entity.vo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import cn.afterturn.easypoi.excel.annotation.Excel;

@Data
public class BillDetailExcel extends ExcelErroMsg{

	//@NotEmpty(message = "箱号不能为空")
	//@Size(max = 30, message = "箱号长度不能超过30")
	@Excel(name="箱号")
    private String boxCode;

	@Excel(name="费用项名称")
	@NotEmpty(message = "费用项不能为空")
	@Size(max = 60, message = "费用项长度不能超过60")
    private String costName;

	@Excel(name="金额")
	@NotNull(message = "金额不能为空")
	@Digits(integer = 11, fraction = 4, message = "金额格式不符")
    private String amountStr;

	@Excel(name="币种")
	@NotEmpty(message = "币种不能为空")
	@Size(max = 30, message = "币种长度不能超过30")
    private String currency;

}
