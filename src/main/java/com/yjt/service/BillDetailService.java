package com.yjt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjt.entity.po.BillDetail;

public interface BillDetailService extends IService<BillDetail> {

	Page<BillDetail> selectByPage(Page<BillDetail> page, String billCode);

}
