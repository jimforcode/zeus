package com.zeus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DemoController extends BaseController {

	@RequestMapping("index")
	public String demoIndex(Model model) {

		return "index";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mianIndex() {
		return "index";
	}


	@RequestMapping("json")
	@ResponseBody
	public Map<String, Object> demoJson() {
		return resultOK();
	}

	@RequestMapping("data")
	public String demoData(Model model) {
		return "index";
	}

	@RequestMapping("login")
	public String demoLoginx(Model model) {
		model.addAttribute("name", "hi");
		return "login";
	}

}
