package com.yjt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjt.entity.po.Company;
import com.yjt.mapper.CompanyMapper;
import com.yjt.service.CompanyService;

@CacheConfig(cacheNames = "company")
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper,Company> implements CompanyService {

	@Autowired
	private CompanyMapper companyMapper;
	
	@Override
	public List<Company> selectAll() {
		 
		return companyMapper.selectAll();
	}

	@Cacheable(key="#root.caches[0].name + '_companyCode_' + #companyCode")
	@Override
	public Company selectByCompanyCode(String companyCode) {
		System.out.println("companyCode="+companyCode);
		return companyMapper.selectByCompanyCode(companyCode);
	}

	@Override
	public Company selectByCompanyName(String companyName) {
		return companyMapper.selectByCompanyName(companyName);
	}

	@Override
	public Page<Company> selectPage(Page<Company> page, Map<String, String> map) {
		return baseMapper.selectPage(page, map);
	}

	@CacheEvict(key="#root.caches[0].name + '_companyCode_' + #company.companyCode")
	@Override
	public void insert(Company company) {
		baseMapper.insert(company);
	}

	@CacheEvict(key="#root.caches[0].name + '_companyCode_' + #company.companyCode")
	@Override
	public void deleteById(Company company) {
		baseMapper.deleteById(company.getId());
	}

	@Override
	@Async//@EnableAsync  springboot启动类加这个注解
	public void asyncTest() throws InterruptedException {
		
		Thread.sleep(10000);
		System.out.println("等了5s");
	}
}
