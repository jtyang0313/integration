package com.yjt.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjt.entity.po.Permission;
import com.yjt.entity.po.Role;
import com.yjt.entity.po.RolePermission;
import com.yjt.entity.po.User;
import com.yjt.service.PermissionService;
import com.yjt.service.RolePermissionService;
import com.yjt.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleAction {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RolePermissionService rolePermissionService;

	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/list")
	@PreAuthorize("hasAuthority('role_list')")
	public String list(Page<Role> page, Model model, @ModelAttribute Role role) {

		page = roleService.selectByPage(page, role);
		model.addAttribute("page", page);
		model.addAttribute("roleName", role.getRoleName());

		return "role/list";
	}

	@RequestMapping("/getTree")
	@ResponseBody
	public JSONArray getTree(Integer roleId) {
		Map<String, RolePermission> rolePermMap = new HashMap<String, RolePermission>();
		if (roleId != null) {
			RolePermission rolePerm = new RolePermission();
			rolePerm.setRoleId(roleId);
			QueryWrapper<RolePermission> ew = new QueryWrapper<RolePermission>(rolePerm);
			List<RolePermission> rolePermList = rolePermissionService.list(ew);
			for (RolePermission rp : rolePermList) {
				rolePermMap.put(rp.getPermissionId(), rp);
			}
		}

		JSONArray tree = buildPermissionTree(rolePermMap);
		return tree;
	}

	private JSONArray buildPermissionTree(Map<String, RolePermission> rolePermMap) {
	
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Permission> permissionList =user.getPermissions();
		if (user.getUsername().equals("admin")) {
			 permissionList = permissionService.list(new QueryWrapper<Permission>());
		}else {
			 permissionList = user.getPermissions();
		}
		
		JSONArray permTree = new JSONArray();
		Map<String, JSONObject> permMap = new HashMap<String, JSONObject>();
		for (Permission perm : permissionList) {
			if (perm.getParentId() == null) {
				JSONObject obj = new JSONObject();
				obj.put("text", perm.getPermissionName());
				obj.put("selectable", false);
				obj.put("tags", new JSONArray());
				obj.put("id", perm.getId());
				if (rolePermMap.get(perm.getId()) != null) {
					JSONObject state = new JSONObject();
					state.put("checked", true);
					obj.put("state", state);
				}
				permTree.add(obj);
				permMap.put(perm.getId(), obj);
			}
		}
		for (Permission perm : permissionList) {
			if (StringUtils.length(perm.getParentId()) == 2) {
				JSONObject parent = permMap.get(perm.getParentId());
				JSONArray nodes = parent.getJSONArray("nodes");
				if (nodes == null) {
					parent.put("nodes", new JSONArray());
				}
				JSONObject obj = new JSONObject();
				obj.put("text", perm.getPermissionName());
				obj.put("selectable", false);
				obj.put("tags", new JSONArray());
				obj.put("id", perm.getId());
				if (rolePermMap.get(perm.getId()) != null) {
					JSONObject state = new JSONObject();
					state.put("checked", true);
					obj.put("state", state);
				}
				parent.getJSONArray("nodes").add(obj);
				permMap.put(perm.getId(), obj);
			}
		}
		for (Permission perm : permissionList) {
			if (StringUtils.length(perm.getParentId()) == 4) {
				JSONObject parent = permMap.get(perm.getParentId());
				JSONArray nodes = parent.getJSONArray("nodes");
				if (nodes == null) {
					parent.put("nodes", new JSONArray());
				}
				JSONObject obj = new JSONObject();
				obj.put("text", perm.getPermissionName());
				obj.put("selectable", false);
				obj.put("tags", new JSONArray());
				obj.put("id", perm.getId());
				if (rolePermMap.get(perm.getId()) != null) {
					JSONObject state = new JSONObject();
					state.put("checked", true);
					obj.put("state", state);
				}
				parent.getJSONArray("nodes").add(obj);
				permMap.put(perm.getId(), obj);
			}
		}

		return permTree;
	}

	@RequestMapping(path = "/add", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('role_add')")
	public String addGet(Model model) {

		return "role/add";
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('role_add')")
	public String addPost(String permissionIds, Role role) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		role.setCompanyName(currentUser.getCompanyCode());
		roleService.add(role, permissionIds);
		return "redirect:/role/list";
	}

	@RequestMapping(path = "/edit", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('role_edit')")
	public String editGet(Model model, Integer roleId) {

		Role role = roleService.getById(roleId);
		model.addAttribute("role", role);

		return "role/edit";
	}

	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('role_edit')")
	public String editPost(String permissionIds, Role role) {

		role.setUpdateTime(new Date());
		roleService.edit(role, permissionIds);
		return "redirect:/role/list";
	}

	@RequestMapping("/delete")
	@ResponseBody
	@PreAuthorize("hasAuthority('role_del')")
	public String delete(Integer roleId) {
		return roleService.delete(roleId);
	}

	/**
	 * @Title: isRoleNameExsit @Description: 判断角色名是否存在 @param @param name
	 * 用户新建的用户名 @param @param roleName 用户原先已经存在的角色名 @param @return 设定文件 @return
	 * boolean 返回类型 @throws
	 */
	@RequestMapping("/isRoleNameExsit")
	@ResponseBody
	public boolean isRoleNameExsit(String roleName, String name) {

		if (name.equals(roleName)) {
			return true;
		}
		int count = roleService.count(new QueryWrapper<Role>().eq("role_name", roleName));
		if (count > 0) {
			return false;
		}
		return true;
	}

}
