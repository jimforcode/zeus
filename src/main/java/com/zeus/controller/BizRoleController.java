package com.zeus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.dto.Pagination;
import com.zeus.model.Role;
import com.zeus.service.RoleService;

@Controller
@RequestMapping("/role")
public class BizRoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	
	public Map<String, Object> addRole(Role role) {
		return resultOK(this.roleService.addRole(role));
	}

	public Map<String, Object> updateRole(Role role) {
		return resultOK(this.roleService.addRole(role));
	}

	public Map<String, Object> list(Pagination page) {
		return resultOK(this.roleService.listRoles(page));
	}

}
