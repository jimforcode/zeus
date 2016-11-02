package com.zeus.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.hengyunabc.zabbix.api.ZabbixApi;

@Controller
public class UserController extends BaseController {
	@Autowired
	private HttpSession session;

	@Autowired
	private ZabbixApi zabbixApi;

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login() {
		session.setAttribute("auth", this.zabbixApi.getAuth());
		return resultOK(this.zabbixApi.getAuth());
	}

	@RequestMapping("/test4")
	@ResponseBody
	public Map<String, Object> login2(HttpServletRequest request) {
		return resultOK(request.getSession(true).toString());
	}

	@RequestMapping("/test5")
	@ResponseBody
	public Map<String, Object> login3(HttpServletRequest request) {
		return resultOK(request.getSession().toString());
	}
}
