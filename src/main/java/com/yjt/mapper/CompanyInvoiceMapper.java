package com.yjt.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.entity.po.CompanyInvoice;

public interface CompanyInvoiceMapper extends BaseMapper<CompanyInvoice>{

	List<CompanyInvoice> selectByCompanyCode(Page<CompanyInvoice> page, String companyCode);

	CompanyInvoice selectBycompanyRise(String companyRise);

	CompanyInvoice selectByDutyParagraph(String dutyParagraph);

	List<CompanyInvoice> selectList(String companyCode);

}