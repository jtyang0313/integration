package com.yjt.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjt.entity.po.BillDetail;
import com.yjt.mapper.BillDetailMapper;
import com.yjt.service.BillDetailService;

@Service
public class BillDetailServiceImpl extends ServiceImpl<BillDetailMapper, BillDetail> implements BillDetailService {

	@Override
	public Page<BillDetail> selectByPage(Page<BillDetail> page, String billCode) {
		return baseMapper.selectByPage(page, billCode);
	}
	

}
