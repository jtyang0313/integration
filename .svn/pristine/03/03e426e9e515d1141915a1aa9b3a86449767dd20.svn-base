package com.yjt.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.entity.po.CompanyInvoice;
import com.yjt.entity.po.User;
import com.yjt.entity.vo.ResponseResult;
import com.yjt.enums.YesNoEnum;
import com.yjt.service.CompanyInvoiceService;

@Slf4j
@Controller
@RequestMapping("/companyInvoice")
public class CompanyInvoiceAction {

    @Autowired
    CompanyInvoiceService companyInvoiceService;
   
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('invoice_list')")
    public String list(Model model, Page<CompanyInvoice> page){
    	
    	User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryWrapper<CompanyInvoice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_code",user.getCompanyCode());
        queryWrapper.orderByDesc("update_time");
        page = (Page<CompanyInvoice>) companyInvoiceService.page(page, queryWrapper);
        model.addAttribute("page",page);
        model.addAttribute("yesNoMap",YesNoEnum.getMap());
        
        return  "companyInvoice/list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    @PreAuthorize("hasAuthority('invoice_del')")
    public ResponseResult<?> del(@RequestParam("id") Integer id){
    	//判断开票是否被引用，引用则不能删除
    	
    	try {
    		companyInvoiceService.removeById(id);
		} catch (Exception e) {
			return ResponseResult.fail("删除失败");
		}
    	
    	return ResponseResult.ok("删除成功");
    }

    @RequestMapping("/doAdd")
    @ResponseBody
    @PreAuthorize("hasAuthority('invoice_add')")
    public ResponseResult<?> doAdd(@ModelAttribute CompanyInvoice companyInvoice){
    	
    	//新增或更新
		ResponseResult<?> result = companyInvoiceService.saveOrUpdateInvoice(companyInvoice);
		
        return result;
    }

    @ResponseBody
	@RequestMapping("/check")
	public boolean check(String companyRise, String oldRise, String dutyParagraph, String oldDutyParagraph){
		log.info("开票抬头税号重复校验...companyRise={},oldRise={},dutyParagraph={},oldDutyParagraph={}"
				,companyRise,oldRise,dutyParagraph,oldDutyParagraph);
		CompanyInvoice companyInvoice = null;
		if(companyRise != null){
			if(companyRise.equals(oldRise)){
				return true;
			}
			companyInvoice = companyInvoiceService.selectBycompanyRise(companyRise);
		}
		if(dutyParagraph != null){
			if(dutyParagraph.equals(oldDutyParagraph)){
				return true;
			}
			companyInvoice = companyInvoiceService.selectByDutyParagraph(dutyParagraph);
		}
		if(companyInvoice == null){
			return true;
		}
		return false;
	}
    
}
