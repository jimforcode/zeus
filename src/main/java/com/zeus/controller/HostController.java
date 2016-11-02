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

/**
 * 
 * 
 * @author zengjin
 *
 */
@Controller
@RequestMapping("host")
public class HostController extends BaseController {
	@Autowired
	private HttpSession session;

	@Autowired
	private HostService hostService;

	@Autowired
	private ApiCfg apiCfg;

	@RequestMapping("/get")
	@ResponseBody
	public Map<String, Object> hostGet(HttpServletRequest request) {
		String[] hosts = { apiCfg.getZabbixHost() };
		return resultOK(hostService.hostGet(hosts));
	}
}
