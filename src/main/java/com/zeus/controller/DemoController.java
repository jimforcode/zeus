package com.zeus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeus.common.config.ApiCfg;
import com.zeus.dto.Pagination;
import com.zeus.model.User;
import com.zeus.service.UserService;
import com.zeus.zabbix.ZabbixDemo;

@Controller
@RequestMapping("demo")
public class DemoController extends BaseController {
	@Autowired
	private UserService userService;

	@Autowired
	private ZabbixDemo zabbixDemo;

	@Autowired
	private ApiCfg apiCfg;

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
		model.addAttribute("page", page.getFirstResult());
		return "index";
	}

	@RequestMapping("data")
	public String demoData(Model model) {
		model.addAttribute("name", "hi");
		userService.getUserById(1L);
		return "index";
	}

	@RequestMapping("login")
	public String demoLoginx(Model model) {
		model.addAttribute("name", apiCfg.getName());
		return "login";
	}

	@RequestMapping("/api")
	@ResponseBody
	public Map<String, Object> testApi() {
		return resultOK(zabbixDemo.demo());
	}

	@RequestMapping(value = "post", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> methodPost(String name, String age) {
		return resultOK(name);
	}

	@RequestMapping(value = "get")
	@ResponseBody
	public Map<String, Object> methodGet(String name, String age) {
		return resultOK(name);
	}
}
