package com.zeus.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeus.common.config.ApiCfg;
import com.zeus.service.HostService;

import io.github.hengyunabc.zabbix.api.ZabbixApi;

@Controller
public class TestController extends BaseController {
	@Autowired
	private HttpSession session;
	@Autowired
	private ZabbixApi zabbixApi;

	@Autowired
	private HostService hostService;

	@Autowired
	private ApiCfg apiCfg;

	@RequestMapping("/test1")
	@ResponseBody
	public Map<String, Object> login() {

		return resultOK(session);
	}

	@RequestMapping("/test11")
	@ResponseBody
	public Map<String, Object> login111() {
		hostService.isExist(apiCfg.getZabbixHost());
		return resultOK(zabbixApi);
	}

	@RequestMapping("/test2")
	@ResponseBody
	public Map<String, Object> login2(HttpServletRequest request) {
		String[] hosts = { apiCfg.getZabbixHost() };
		hostService.hostGet(hosts);
		return resultOK(request.getSession(true).toString());
	}

	@RequestMapping("/test3")
	@ResponseBody
	public Map<String, Object> login3(HttpSession session) {
		return resultOK(session);
	}
}
