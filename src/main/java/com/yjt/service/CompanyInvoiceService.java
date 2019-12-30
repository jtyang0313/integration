package com.yjt.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjt.entity.po.CompanyInvoice;
import com.yjt.entity.vo.ResponseResult;

public interface CompanyInvoiceService extends IService<CompanyInvoice> {

	Page<CompanyInvoice> selectByCompanyCode(Page<CompanyInvoice> page, String companyCode);

	CompanyInvoice selectBycompanyRise(String companyRise);

	CompanyInvoice selectByDutyParagraph(String dutyParagraph);

	List<CompanyInvoice> selectByCompanyCode(String companyCode);
	
	//保存或更新开票
	ResponseResult<?> saveOrUpdateInvoice(CompanyInvoice companyInvoice);
}
