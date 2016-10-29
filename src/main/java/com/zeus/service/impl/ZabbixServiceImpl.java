package com.zeus.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.config.ApiCfg;
import com.zeus.dto.zabbix.AuthResult;
import com.zeus.service.ZabbixService;

import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

@Service
public class ZabbixServiceImpl implements ZabbixService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApiCfg apiCfg;

	@Autowired
	private ZabbixApi zabbixApi;

	/**
	 * 认证
	 */
	@Override
	public boolean auth(String username, String pwd) {
		boolean result = this.zabbixApi.login(username, pwd);
		// String url = apiCfg.getZabbixAuthUrl();
		// DefaultZabbixApi zabbixApi = new DefaultZabbixApi(url);
		// zabbixApi.init();
		// Request request = RequestBuilder.newBuilder().paramEntry("user",
		// username).paramEntry("password", pwd)
		// .method("user.login").build();
		// JSONObject response = zabbixApi.call(request);
		// AuthResult result = JSONObject.toJavaObject(response,
		// AuthResult.class);
		// logger.trace("json �����{}", result);
		return result;
	}

	@Override
	public Object hostGet(String[] hosts) {
		boolean login = zabbixApi.login("Admin", "zabbix");
		JSONObject getResponse = new JSONObject();
		if (login) {
			JSONObject filter = new JSONObject();
			filter.put("host", hosts);
			Request getRequest = RequestBuilder.newBuilder().method("host.get").paramEntry("filter", filter).build();
			getResponse = zabbixApi.call(getRequest);
			return getResponse;
		}
		// System.err.println(getResponse);
		// String hostid =
		// getResponse.getJSONArray("result").getJSONObject(0).getString("hostid");
		// System.err.println(hostid);
		return getResponse;
	}

}
