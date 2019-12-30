package com.yjt.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.entity.po.Bill;
import com.yjt.entity.po.BillDetail;
import com.yjt.entity.po.Company;
import com.yjt.entity.vo.BillDetailExcel;
import com.yjt.entity.vo.BillExcel;
import com.yjt.entity.vo.ResponseResult;
import com.yjt.enums.BillStateEnum;
import com.yjt.enums.EnumCurrencyType;
import com.yjt.service.BillDetailService;
import com.yjt.service.BillService;
import com.yjt.service.CompanyService;

@Slf4j
@RequestMapping("/bill")
@Controller
public class BillController {
	
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	@Autowired
	BillService billService;
	@Autowired
	BillDetailService billDetailService;
	@Autowired
	CompanyService companyService;
	
	@RequestMapping("/list")
	public String list(Model model, Page<Bill> page, @RequestParam Map<String, String> map){
		
		String billDateStr = map.get("billDate");
		if(StringUtils.isNotBlank(billDateStr)){
            String[] billDateArray = billDateStr.split("~");
            map.put("startBillDate", billDateArray[0].trim());
            map.put("endBillDate", billDateArray[1].trim());
        }
		page = billService.selectByPage(page, map);
		List<Company> companyList = companyService.selectAll();
		
		model.addAttribute("companyList", companyList);
		model.addAttribute("stateMap", BillStateEnum.getMap());
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		model.addAttribute("enumCurrencyMap", EnumCurrencyType.toMap());
		return "bill/list";
	}
	
	@RequestMapping("/detail")
	public String detail(Model model, Page<BillDetail> page, String billCode){
		
		Bill bill = billService.getOne(new QueryWrapper<Bill>().eq("bill_code", billCode));
		Company company = companyService.getOne(new QueryWrapper<Company>().eq("company_code", bill.getCompanyCode()));
		bill.setCompanyName(company.getCompanyName());
		page = billDetailService.selectByPage(page, billCode);
		
		model.addAttribute("bill", bill);
		model.addAttribute("page", page);
		model.addAttribute("stateMap", BillStateEnum.getMap());
		model.addAttribute("enumCurrencyMap", EnumCurrencyType.toMap());
		return "bill/detail";
	}
	
	@ResponseBody
	@RequestMapping("/check")
	public ResponseResult<?> check(Model model, Bill bill){
		
		billService.updateById(bill);
		return ResponseResult.ok();
	}
	
	/**
	 * 账单导入
	 * @param file
	 * @param billCode
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/importExcel")
	public ResponseResult<?> importExcel(@RequestParam("file") MultipartFile file) throws Exception{
		
			String fileName = file.getOriginalFilename();
			log.info(fileName);
			
			//读取表头
			ImportParams headParams = new ImportParams();
			headParams.setTitleRows(2);// 过滤前两行
			headParams.setReadRows(1);
			List<BillExcel> headList = null;
			//读取表体
			ImportParams bodyParams = new ImportParams();
			bodyParams.setTitleRows(5);
			List<BillDetailExcel> bodyList = null;
			
			try {
				headList = ExcelImportUtil.importExcel(file.getInputStream(), BillExcel.class, headParams);
				bodyList = ExcelImportUtil.importExcel(file.getInputStream(), BillDetailExcel.class, bodyParams);
				file.getInputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
				log.error("excel读取失败{}", e);
			}
			//校验
			ExcelImportResult<BillExcel> checkHeadExcel = checkHeadExcel(headList);
			ExcelImportResult<BillDetailExcel> checkBodyExcel = checkBodyExcel(bodyList);
			//有待优化 校验
			if(checkHeadExcel.getFailList() != null && checkHeadExcel.getFailList().size() >0){
				StringBuilder stringBuilder = new StringBuilder("表头：");
				for(BillExcel e : checkHeadExcel.getFailList()){
					stringBuilder.append(e.getErrorMsg());
				}
				return ResponseResult.fail(stringBuilder.toString());
			}
			if(checkHeadExcel.getFailList() != null && checkBodyExcel.getFailList().size() >0){
				StringBuilder stringBuilder = new StringBuilder("表体：");
				for(BillDetailExcel e : checkBodyExcel.getFailList()){
					stringBuilder.append(e.getErrorMsg());
				}
				return ResponseResult.fail(stringBuilder.toString());
			}
			
			//保存数据
			billService.saveExcelBill(headList, bodyList);
			
			return ResponseResult.ok(200, "导入成功！");
	}
	
	/**
	 * 账单表头校验
	 * @param list
	 * @param billCode
	 * @return
	 */
	public ExcelImportResult<BillExcel> checkHeadExcel(List<BillExcel> list)
	{
		// 表体校验
		Validator validator = factory.getValidator();
		ExcelImportResult<BillExcel> bodyImportResult = new ExcelImportResult<>();
		List<BillExcel> successList = new ArrayList<>();
		List<BillExcel> failList = new ArrayList<>();	
		BillExcel billExcelHead = list.get(0);
		
		log.info("=======账单{}进行格式检验=======", billExcelHead.getBillCode());
		Set<ConstraintViolation<BillExcel>> bodyViolations = validator.validate(billExcelHead);
		for (ConstraintViolation<BillExcel> constraintViolation : bodyViolations)
		{
			billExcelHead.setErrorMsg(billExcelHead.getErrorMsg() + ";" + constraintViolation.getMessage());
		}
		if (StringUtils.isNotBlank(billExcelHead.getErrorMsg()))
		{
			String errorMsg = billExcelHead.getErrorMsg().replace("null;", "");
			billExcelHead.setErrorMsg(errorMsg);
			failList.add(billExcelHead);
		} else
		{
			successList.add(billExcelHead);
		}
		log.info("============格式校验结束============");
		log.info("============数据校验开始============");
		if (failList.size() == 0)
		{
			//判断导入的账单编号是否已经存在
			StringBuilder stringBuilder = new StringBuilder();
			Bill agentBill = billService.selectByBillCode(billExcelHead.getBillCode());
			if(agentBill != null){
				log.info("账单编号：{}已存在，不能重复导入!",agentBill.getBillCode());
				stringBuilder.append("账单编号:"+agentBill.getBillCode()+"已存在，不能重复导入!!");
			}
			
			Company company = companyService.selectByCompanyName(billExcelHead.getCompanyName());
			if(company == null){
				log.info("企业:{}不存在!",billExcelHead.getCompanyName());
				stringBuilder.append("企业:"+billExcelHead.getCompanyName()+"不存在!");
			}
			
			if(StringUtils.isNotBlank(stringBuilder)){
				billExcelHead.setErrorMsg(stringBuilder.toString());
			}
			if (StringUtils.isNotBlank(billExcelHead.getErrorMsg())){
				failList.add(billExcelHead);
			}
			
		}
		log.info("===============数据校验结束============");
		
		bodyImportResult.setFailList(failList);
		bodyImportResult.setList(successList);
		return bodyImportResult;
	}
	
	/**
	 * 账单表体校验
	 * @param list
	 * @return
	 */
	public ExcelImportResult<BillDetailExcel> checkBodyExcel(List<BillDetailExcel> list)
	{
		// 表体校验
		Validator validator = factory.getValidator();
		ExcelImportResult<BillDetailExcel> bodyImportResult = new ExcelImportResult<BillDetailExcel>();
		List<BillDetailExcel> successList = new ArrayList<>();
		List<BillDetailExcel> failList = new ArrayList<>();				
		for (BillDetailExcel billExcelBody : list)
		{
			log.info("=============表体数据校验============");
			Set<ConstraintViolation<BillDetailExcel>> bodyViolations = validator.validate(billExcelBody);
			for (ConstraintViolation<BillDetailExcel> constraintViolation : bodyViolations)
			{
				billExcelBody.setErrorMsg(billExcelBody.getErrorMsg() + ";" + constraintViolation.getMessage());
			}
			if (StringUtils.isNotBlank(billExcelBody.getErrorMsg()))
			{
				String errorMsg = billExcelBody.getErrorMsg().replace("null;", "");
				billExcelBody.setErrorMsg(errorMsg);
				failList.add(billExcelBody);
			} else
			{
				successList.add(billExcelBody);
			}
		
			if (failList.size() == 0)
			{
				StringBuilder stringBuilder = new StringBuilder();
				//判断费用项是否存在
//				Cost cost = costService.getByCostName(billExcelBody.getCostName());
//				if(cost == null){
//					log.info("费用项：{}不存在!", billExcelBody.getCostName());
//					stringBuilder.append("费用项："+billExcelBody.getCostName()+"不存在!");
//				}
				
				if(StringUtils.isNotBlank(stringBuilder)){
					billExcelBody.setErrorMsg(stringBuilder.toString());
				}
				if (StringUtils.isNotBlank(billExcelBody.getErrorMsg())){
					failList.add(billExcelBody);
				}
		}
		
		}
		log.info("===============表体数据校验结束============");

		bodyImportResult.setFailList(failList);
		bodyImportResult.setList(successList);
		return bodyImportResult;
	}
	
	/**
	 * 导出excel
	 * @param response
	 * @param ids
	 * @param predictionModel
	 * @throws Exception
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response, String ids) throws Exception
	{
		Workbook wb = new XSSFWorkbook();
		Sheet s = wb.createSheet();
		wb.setSheetName(0, "出库交接导出数据");
		CellStyle cs = wb.createCellStyle();
		CellStyle cs2 = wb.createCellStyle();
		s.setColumnWidth(3, 12*256);
		s.setColumnWidth(6, 20*256);
		Row row = s.createRow(0);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("账单编号");
		cell0.setCellStyle(cs);
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("企业编码");
		cell1.setCellStyle(cs);
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("账单金额");
		cell2.setCellStyle(cs);
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("账单日期");
		cell3.setCellStyle(cs);
		Cell cell4 = row.createCell(4);
		cell4.setCellValue("状态");
		cell4.setCellStyle(cs);
		Cell cell5 = row.createCell(5);
		cell5.setCellValue("备注");
		cell5.setCellStyle(cs);
		Cell cell7 = row.createCell(6);
		cell7.setCellValue("创建时间");
		cell7.setCellStyle(cs);
		
		Bill bill2 = billService.getById(ids);
		List<Bill> list = new ArrayList<>();
		list.add(bill2);
		
		DataFormat format = wb.createDataFormat();
		DataFormat format2 = wb.createDataFormat();
		int count = 0;
		for (int i = 0; i < list.size(); i++)
		{
			Bill bill = list.get(i);
			
			Row r = s.createRow(count + 1);
			Cell tallyNo = r.createCell(0);
			tallyNo.setCellValue(bill.getBillCode());
			Cell plateNo = r.createCell(1);
			plateNo.setCellValue(bill.getCompanyCode());
			Cell preCode = r.createCell(2);
			preCode.setCellValue(bill.getBillAmount());
			
			Cell bigCode = r.createCell(3);
			cs.setDataFormat(format.getFormat("yyyy-mm-dd"));
			bigCode.setCellStyle(cs);
			bigCode.setCellValue(bill.getBillDate());
			Cell smallCode = r.createCell(4);
			smallCode.setCellValue(bill.getState());
			Cell createTime = r.createCell(5);
			createTime.setCellValue(bill.getBillMemo());
			Cell updateTime = r.createCell(6);
			updateTime.setCellValue(bill.getCreateTime());
			cs2.setDataFormat(format2.getFormat("yyyy-m-d HH:mm:ss"));
			updateTime.setCellStyle(cs2);
			
			count++;
		}
		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			wb.write(out);

			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition",
					"attachment;filename=export.xlsx");
			response.setContentLength(out.toByteArray().length);
			response.getOutputStream().write(out.toByteArray());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		catch (IOException e)
		{
			log.error(e.getMessage());
		}
	}
}
