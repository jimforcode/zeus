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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeus.common.config.ApiCfg;
import com.zeus.dto.Pagination;
import com.zeus.dto.login.UrlAndPermission;
import com.zeus.model.User;
import com.zeus.service.UserService;

@Controller
@RequestMapping("demo")
public class DemoController extends BaseController {
	@Autowired
	private UserService userService;

	@Autowired
	private ApiCfg apiCfg;

	@Autowired
	private HttpSession session;

	@RequestMapping("index")
	public String demoIndex(Model model) {
		User user = this.userService.getUserById(1L);
		model.addAttribute("name", user.getAlias());
		return "index";
	}

	@RequestMapping("json")
	@ResponseBody
	public Map<String, Object> demoJson(Pagination page) {
		return resultOK(this.userService.listUsers(page));
	}

	@RequestMapping("page")
	public String demoPage(Pagination page, Model model) {
		page.setTotal(100);
		page.setCountPage(1);

		model.addAttribute("page", page);
		return "index";

	}

	@RequestMapping("data")
	public String demoData(Model model) {
		model.addAttribute("name", "hi");
		userService.getUserById(1L);
		return "index";
	}

	@RequestMapping("login")
	public String demoLoginx(Model model, Long userId) {
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

	@RequestMapping(value = "post", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> methodPost(String name, String age) {
		return resultOK(name);
	}

}
