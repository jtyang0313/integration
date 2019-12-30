package com.yjt.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.entity.po.BillDetail;

public interface BillDetailMapper extends BaseMapper<BillDetail> {

	Page<BillDetail> selectByPage(Page<BillDetail> page, @Param("billCode")String billCode);

}