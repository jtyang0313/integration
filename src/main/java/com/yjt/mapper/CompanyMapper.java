package com.yjt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.entity.po.Company;

public interface CompanyMapper extends BaseMapper<Company> {
	
	List<Company>selectAll();

	Company selectByCompanyCode(String companyCode);

	Company selectByCompanyName(String companyName);

	Page<Company> selectPage(Page<Company> page, @Param("map")Map<String, String> map);
}