package com.yjt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.entity.po.Bill;

public interface BillMapper extends BaseMapper<Bill> {

	List<Bill> selectByPage(Page<Bill> page, @Param("map")Map<String, String> map);

	Bill selectByBillCode(String billCode);

}