package com.zeus.controller;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zeus.common.config.ApiCfg;
import com.zeus.dto.login.UrlAndPermission;
import com.zeus.model.User;
import com.zeus.service.UserService;

@Controller
@RequestMapping("/user")
public class BizUserController extends BaseController {

	@Autowired
	UserService userService;

	@Autowired
	private ApiCfg apiCfg;

	@Autowired
	private HttpSession session;

	@RequestMapping("add")
	public Map<String, Object> addUser(User user) {
		return resultOK(this.userService.addUser(user));
	}

	@RequestMapping("del")
	public Map<String, Object> delUser(User user) {
		return resultOK(this.userService.addUser(user));
	}

	@RequestMapping("update")
	public Map<String, Object> updateUser(User user) {
		return resultOK(this.userService.update(user));
	}

	@RequestMapping("login")
	public String demoLoginx(Model model, Long userId) {
		model.addAttribute("name", apiCfg.getZabbixAuthUrl());
		List<String> menus = this.userService.listMenuByUser(userId);
		List<UrlAndPermission> menusAndPermissions = this.userService.getMenuPermissionByUser(userId);

		Map<String, HashSet<String>> auths = new LinkedHashMap<String, HashSet<String>>();
		// �˵�Ȩ��
		for (UrlAndPermission entity : menusAndPermissions) {
			if (auths.containsKey(entity.getMenuUrl())) {
				HashSet<String> permissions = auths.get(entity.getMenuUrl());
				permissions.add(entity.getName());
			} else {
				HashSet<String> permissions = new HashSet<String>();
				permissions.add(entity.getName());
				auths.put(entity.getMenuUrl(), permissions);
			}
		}

		session.setAttribute("meues", menus);
		session.setAttribute("auths", auths);
		return "login";
	}

}
