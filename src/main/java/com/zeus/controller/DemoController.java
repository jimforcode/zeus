package com.zeus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeus.model.User;
import com.zeus.service.UserService;

@Controller
@RequestMapping("demo")
public class DemoController extends BaseController {
	@Autowired
	private UserService userService;
   
	@RequestMapping("index")
	public String demoIndex(Model model) {
		User user = this.userService.getUserById(1L);
		model.addAttribute("name", user.getAlias());

		return "index";
	}

	@RequestMapping("json")
	@ResponseBody
	public Map<String, Object> demoJson() {
		return resultOK();
	}

	@RequestMapping("data")
	public String demoData(Model model) {
		model.addAttribute("name", "hi");
		userService.getUserById(1L);
		return "index";
	}

	@RequestMapping("login")
	public String demoLoginx(Model model) {
		model.addAttribute("name", "hi");
		return "login";
	}

}
