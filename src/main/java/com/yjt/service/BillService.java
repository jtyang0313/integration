package com.yjt.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjt.entity.po.Bill;
import com.yjt.entity.vo.BillDetailExcel;
import com.yjt.entity.vo.BillExcel;

public interface BillService extends IService<Bill> {

	Page<Bill> selectByPage(Page<Bill> page, Map<String, String> map);

	Bill selectByBillCode(String billCode);

//	//根据id删除整个账单数据
//	void deleteBill(Integer id);

	//保存excel导入账单
	void saveExcelBill(List<BillExcel> headList,
			List<BillDetailExcel> bodyList) throws Exception;
	
}
