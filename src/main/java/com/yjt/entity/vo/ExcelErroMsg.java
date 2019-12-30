package com.yjt.entity.vo;

import cn.afterturn.easypoi.handler.inter.IExcelDataModel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;

public class ExcelErroMsg implements IExcelModel,IExcelDataModel{

	private String errorMsg; 
	
	public int rowNum;

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public int getRowNum() {
		return rowNum;
	}

	@Override
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
}
