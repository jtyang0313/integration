package com.yjt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.config.mq.CompanySender;
import com.yjt.entity.po.Company;
import com.yjt.entity.po.CompanyFile;
import com.yjt.entity.po.User;
import com.yjt.entity.vo.ResponseResult;
import com.yjt.enums.CompanyTypeEnum;
import com.yjt.service.CompanyFileService;
import com.yjt.service.CompanyService;
import com.yjt.service.UserService;

@Slf4j
@Controller
@RequestMapping("/company")
public class CompanyAction {

    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyFileService companyFileService;
    @Autowired
    UserService userService;

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('comapny_list')")
    public String list(Model model, Page<Company> page, @RequestParam Map<String, String> map){
    	String dateStr = map.get("updateTime");
		if(StringUtils.isNotBlank(dateStr)){
            String[] billDateArray = dateStr.split("~");
            map.put("startUpdateDate", billDateArray[0].trim());
            map.put("endUpdateDate", billDateArray[1].trim());
        }
		page = companyService.selectPage(page, map);
        model.addAttribute("page",page);
        model.addAttribute("enumCompanyType", CompanyTypeEnum.values());
        model.addAttribute("companyTypeMap", CompanyTypeEnum.getMap());
        return  "company/list";
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('company_add')")
    public String add(Model model){
        model.addAttribute("enumCompanyType",CompanyTypeEnum.values());
        return  "company/add";
    }
    
    @RequestMapping("/doAdd")
    @ResponseBody
    @PreAuthorize("hasAuthority('company_add')")
    public ResponseResult<?> doAdd(@ModelAttribute Company addCompany){
        Company checkCompany = companyService.selectByCompanyCode(addCompany.getCompanyCode());
        if(checkCompany != null){
            return ResponseResult.fail("账户编码已经存在");
        }
        companyService.insert(addCompany);
        return ResponseResult.ok();
    }
    
    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('company_edit')")
    public String edit(Model model,@RequestParam("id") Integer id){
        Company company = companyService.getById(id);
        model.addAttribute("enumCompanyType",CompanyTypeEnum.values());
        model.addAttribute("company",company);
        return  "company/edit";
    }
    
    @RequestMapping("/doEdit")
    @ResponseBody
    @PreAuthorize("hasAuthority('company_edit')")
    public ResponseResult<?> doEdit(@ModelAttribute Company updateCompany){
        Company company = new Company();
        BeanUtils.copyProperties(updateCompany,company);
        companyService.updateById(company);
        return ResponseResult.ok();
    }
    
    @RequestMapping("/detail")
    @PreAuthorize("hasAuthority('company_detail')")
    public String detail(Model model,@RequestParam("id") Integer id){
        Company company = companyService.getById(id);
        
        List<CompanyFile> fileList = companyFileService.list(new QueryWrapper<CompanyFile>().eq("company_code", company.getCompanyCode()));
        for(CompanyFile file : fileList){
        	User user = userService.getById(file.getUserId());
        	file.setUserName(user.getUsername());
        }
        model.addAttribute("companyTypeMap",CompanyTypeEnum.getMap());
        model.addAttribute("company",company);
        model.addAttribute("fileList",fileList);
        return  "company/detail";
    }

    
    @RequestMapping("/del")
    @ResponseBody
    @PreAuthorize("hasAuthority('company_delete')")
    public ResponseResult<?> del(@RequestParam("id") Integer id){
    	Company company = companyService.getById(id);
        companyService.deleteById(company);
        return ResponseResult.ok();
    }
    
    /**
     * 校验企业编码是否存在
     * @param companyCode
     * @return
     */
    @RequestMapping("/checkCode")
    @ResponseBody
    @PreAuthorize("hasAuthority('company_add') or hasAuthority('company_edit')")
    public boolean checkCode(@RequestParam("companyCode") String companyCode){
        Company checkCompany = companyService.selectByCompanyCode(companyCode);
        if(checkCompany == null){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 校验企业名称是否存在
     * @param companyCode
     * @return
     */
    @RequestMapping("/checkName")
    @ResponseBody
    @PreAuthorize("hasAuthority('company_add') or hasAuthority('company_edit')")
    public boolean checkName(@RequestParam("companyName") String companyName){
        Company checkCompany = companyService.selectByCompanyName(companyName);
        if(checkCompany == null){
            return true;
        }else{
            return false;
        }
    }
    
    /**
	 * 上传附件
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile[] files, CompanyFile addCompanyFile, HttpServletRequest request) {

		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			String filePath = "D:/tmp";
			File fileDir = new File(filePath);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}
			
			try {
				String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
				file.transferTo(new File(fileDir.getAbsolutePath(), newFileName));
				// 保存文件
				CompanyFile companyFile = new CompanyFile();
				companyFile.setCompanyCode(addCompanyFile.getCompanyCode());
				companyFile.setFileName(fileName);
				companyFile.setFilePath(filePath+"/"+newFileName);
				companyFile.setUserId(currentUser.getId());
				companyFile.setMemo(addCompanyFile.getMemo());
				companyFile.setUploadTime(new Date());
				companyFileService.save(companyFile);
				log.info("上传成功:fileName={}", fileName);
			} catch (IOException e) {
				log.info(e.getMessage());
			}

		}
		log.info("保存数据成功，共{}个文件", files.length);
		return "ok";
	}

	/**
	 * 文件下载
	 *
	 * @return
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping("/downloads")
	public void downloadFile(Integer id, HttpServletResponse response) throws FileNotFoundException, UnsupportedEncodingException {
		CompanyFile companyFile = companyFileService.getById(id);

		String fileName = companyFile.getFileName();
		String filePath = companyFile.getFilePath();
		fileName = URLEncoder.encode(fileName,"utf-8");
		// 读到流中
		InputStream inStream = new FileInputStream(filePath);// 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType("bin");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 循环取出流中的数据
		byte[] b = new byte[100];
		int len;
		try {
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e) {
			log.error("文件读写错误", e);
		}
	}
	
	@Autowired
	CompanySender companySender;
	
	/**
	 * 测试mq发送
	 */
	@ResponseBody
	@RequestMapping("/send")
	public void send(){
		Company company = new Company();
		company.setCompanyCode("123");
		company.setCompanyName("yjt");
		company.setTelPhone("18766");
		companySender.sendMsg(company);
		System.out.println("dds");
	}
	
	//测试乱码
	@ResponseBody
	@RequestMapping("/encoding")
	public String encoding(){
		System.out.println("dds");
		return "成功";
	}
}
