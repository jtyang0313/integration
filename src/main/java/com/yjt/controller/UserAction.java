package com.yjt.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.entity.po.Company;
import com.yjt.entity.po.Role;
import com.yjt.entity.po.User;
import com.yjt.service.CompanyService;
import com.yjt.service.RoleService;
import com.yjt.service.UserService;
 
@Slf4j
@Controller
@RequestMapping("/user")
public class UserAction {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/list")
	@PreAuthorize("hasAuthority('uesr_list')")
	public ModelAndView list(Page<User> page, Model model, @ModelAttribute("user") User user)
	{
		log.info("分页查询用户列表参数...... page={}",JSON.toJSON(page));
		User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (cUser.getUsername().equals("admin")) {
			page = userService.selectByPage(page, user);
		}else{
			user.setCompanyCode(cUser.getCompanyCode());
			page = userService.selectByPage(page, user);
		}
		log.info("分页查询用户结果... list={}",page.getRecords().toString());
		model.addAttribute("page", page);
		model.addAttribute("user", user);
		return new ModelAndView("user/list");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('user_add')")
	public String toAdd(Model model, @ModelAttribute("user") User user){
		
		User cUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Role> roleList = roleService.list();
		List<Company>companyList=new ArrayList<>();
		if (cUser.getUsername().equals("admin")) {
			  companyList = companyService.selectAll();
		}else {
			  companyList = companyService.list(new QueryWrapper<Company>().eq("company_code", cUser.getCompanyCode()));
		}
	
		model.addAttribute("roleList", roleList);
		model.addAttribute("companyList", companyList);
		return "user/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('user_add')")
	public ModelAndView doAdd(Model model, @ModelAttribute("user") User user)
	{
		log.info("新增用户...user={}",user.toString());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setCreateTime(new Date());
		userService.insert(user);
		
		return new ModelAndView("redirect:/user/list");
	}

	@ResponseBody
	@RequestMapping("/userName")
	public boolean getName(String nickName, String userName){
		log.info("判断该昵称是否存在...name={}",nickName);
		if (userName.equals(nickName)) {
			return true;
		}
		User user = userService.findByName(userName);
		if (user == null)
		{
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('user_edit')")
	public String toEdit(Model model, Integer id)
	{
		log.info("修改用户的id...id={}",id);
		User user = userService.selectById(id);
		if (user != null)
		{
			model.addAttribute("user", user);
		}
		List<Role> roleList = roleService.list();
		for(Role role : roleList){
			role.setRoleSign(null);
			if(null!=user.getRoleName() && user.getRoleName().indexOf(role.getRoleName()) !=-1){
			   role.setRoleSign("true");
		   }
		}
		List<Company> companyList = companyService.selectAll();
		model.addAttribute("companyList", companyList);
		model.addAttribute("roleList", roleList);
		 
		return "user/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('user_edit')")
	public String doEdit(Model model,@ModelAttribute("user") User user)
	{
		log.info("编辑用户...user={}",user.toString());
	 
		user.setUpdateTime(new Date());
		userService.updateUser(user);
		return "redirect:/user/list";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('user_del')")
	public JSONObject delete(Integer id)
	{
		JSONObject object = new JSONObject();
		Boolean flag = false;
		try{
			flag = userService.deleteUser(id);
		}catch(Exception e){
			object.put("msg", "删除出错了");
			log.error("删除用户...... 出错！",e);
		}
		object.put("flag", flag);
		return object;
	}
	
	@RequestMapping("/resetPwd")
	@ResponseBody
	@PreAuthorize("hasAuthority('reset_pwd')")
	public boolean resetPwd(Integer id) {
		User user = userService.getById(id);
		log.info("重置密码...... userId={}",id);
		try {
			if (user!=null) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String pwd = encoder.encode("123456");
				user.setPassword(pwd);
				userService.updateById(user);
				return true;
			}
		} catch (Exception e) {
			log.error("重置密码...... 出错！",e);
		}
		return false;
	}
	
	@RequestMapping(value = "/changePwd", method = RequestMethod.GET)
	public String toChangePwd(Integer id, Model model) {
		return  "user/changePwd";
	}
	
	@ResponseBody
	@RequestMapping(value = "/changePwd", method = RequestMethod.POST)
	public boolean doChangePwd(String oldPassword, String newPassword){
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(encoder.matches(oldPassword, user.getPassword())){
			user.setPassword(encoder.encode(newPassword));
			userService.updateById(user);
			return true;
		}
		return false;
	}
	
}
