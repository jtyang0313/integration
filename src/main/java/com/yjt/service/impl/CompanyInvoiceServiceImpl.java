package com.yjt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjt.entity.po.CompanyInvoice;
import com.yjt.entity.po.User;
import com.yjt.entity.vo.ResponseResult;
import com.yjt.mapper.CompanyInvoiceMapper;
import com.yjt.service.CompanyInvoiceService;

@Service
public class CompanyInvoiceServiceImpl extends ServiceImpl<CompanyInvoiceMapper, CompanyInvoice>
		implements CompanyInvoiceService {

	@Autowired
	private CompanyInvoiceMapper companyInvoiceMapper;
	
	@Override
	public Page<CompanyInvoice> selectByCompanyCode(Page<CompanyInvoice> page,
			String companyCode) {
		return page.setRecords(companyInvoiceMapper.selectByCompanyCode(page, companyCode));
	}


	@Override
	public CompanyInvoice selectBycompanyRise(String companyRise) {
		return companyInvoiceMapper.selectBycompanyRise(companyRise);
	}

	@Override
	public CompanyInvoice selectByDutyParagraph(String dutyParagraph) {
		return companyInvoiceMapper.selectByDutyParagraph(dutyParagraph);
	}

	@Override
	public List<CompanyInvoice> selectByCompanyCode(String companyCode) {
		return companyInvoiceMapper.selectList(companyCode);
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResponseResult<?> saveOrUpdateInvoice(CompanyInvoice companyInvoice) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	companyInvoice.setCompanyCode(user.getCompanyCode());
    	
    	//如果状态为默认，将其他状态改为不默认
		if(companyInvoice.getState() == 1){
			List<CompanyInvoice> list = companyInvoiceMapper.selectList(companyInvoice.getCompanyCode());
			for(CompanyInvoice invoice : list){
				if(invoice.getState() == 1){
					invoice.setState(0);
					companyInvoiceMapper.updateById(invoice);
				}
			}
		}
		
		if(companyInvoice.getId() != null){
			companyInvoiceMapper.updateById(companyInvoice);
			return ResponseResult.ok("更新成功");
		}
		
		companyInvoiceMapper.insert(companyInvoice);
		return ResponseResult.ok("新增成功");
	}
}
