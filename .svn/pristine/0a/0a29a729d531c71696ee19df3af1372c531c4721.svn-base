package com.yjt.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjt.entity.po.Bill;
import com.yjt.entity.po.BillDetail;
import com.yjt.entity.po.Company;
import com.yjt.entity.vo.BillDetailExcel;
import com.yjt.entity.vo.BillExcel;
import com.yjt.enums.BillStateEnum;
import com.yjt.mapper.BillMapper;
import com.yjt.mapper.CompanyMapper;
import com.yjt.service.BillDetailService;
import com.yjt.service.BillService;
import com.yjt.util.BeanMapper;
import com.yjt.util.MoneyUtils;

@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {

	@Autowired
	BillDetailService billDetailService;
	
//	@Autowired
//	BillService billService;
	
	@Autowired
	CompanyMapper companyMapper;
	
	
	@Override
	public Page<Bill> selectByPage(Page<Bill> page, Map<String, String> map) {
		return page.setRecords(baseMapper.selectByPage(page, map));
	}

	@Override
	public Bill selectByBillCode(String billCode) {
		return baseMapper.selectByBillCode(billCode);
	}
//
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void deleteBill(Integer id) {
//		
//		Bill Bill = BillService.selectById(id);
//		
//		//对账状态改回未对账
//		billDetailService.backDzStatus(Bill.getBillCode());
//		List<BillDetail> detailList = BillDetailService.selectAllByBillCode(Bill.getBillCode());
//		BillService.deleteById(id);
//		
//		for(BillDetail detail : detailList){
//			BillDetailService.deleteById(detail.getId());
//			//删除添加的对账表
//			EntityWrapper<DzSign> wrap = new EntityWrapper<DzSign>();
//			wrap.eq("agent_bill_detail_id", detail.getId());
//			dzSignMapper.delete(wrap);
//		}
//		//删除额外费用
//		EntityWrapper<Extra> ew = new EntityWrapper<Extra>();
//		ew.eq("bill_code", Bill.getBillCode());
//		extraMapper.delete(ew);
//		//删除添加的明细
//		EntityWrapper<BillDetail> ew2 = new EntityWrapper<BillDetail>();
//		ew2.eq("bill_code", Bill.getBillCode());
//		billDetailService.delete(ew2);
//		
//	}
//
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveExcelBill(List<BillExcel> headList, List<BillDetailExcel> bodyList) throws Exception {
		
		BillExcel billExcel = headList.get(0);
		Bill bill = new Bill();
		BeanMapper.copy(billExcel, bill);
		bill.setBillAmount(MoneyUtils.changeY2H(bill.getBillAmountStr()));
		
		Company company = companyMapper.selectByCompanyName(bill.getCompanyName());
		bill.setCompanyCode(company.getCompanyCode());
		bill.setState(BillStateEnum.CHECK_WAIT.getValue());//默认待审核
		baseMapper.insert(bill);
		
		for(BillDetailExcel detail : bodyList){
			
			BillDetail billDetail = new BillDetail();
			BeanMapper.copy(detail, billDetail);
			billDetail.setBillCode(bill.getBillCode());
			billDetail.setAmount(MoneyUtils.changeY2H(detail.getAmountStr()));
			billDetailService.save(billDetail);
		}
	}

}
