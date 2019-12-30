package com.yjt.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjt.entity.po.Company;

public interface CompanyService extends IService<Company> {
	
	List<Company> selectAll();
	
	Company selectByCompanyCode(String companyCode);

	Company selectByCompanyName(String companyName);

	Page<Company> selectPage(Page<Company> page, Map<String, String> map);

	void insert(Company addCompany);

	void deleteById(Company company);
	
	void asyncTest() throws InterruptedException ;
}
