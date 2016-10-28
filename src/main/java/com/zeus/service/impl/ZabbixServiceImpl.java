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
@Service
public class ZabbixServiceImpl implements ZabbixService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	static final String authUrl = "zabbix/api_jsonrpc.php";
	@Autowired
	private ApiCfg apiCfg;

	@Override
	public AuthResult auth(String username, String pwd) {
		String url = apiCfg.getZabbixHost() + authUrl;
		DefaultZabbixApi zabbixApi = new DefaultZabbixApi(url);
		zabbixApi.init();
		Request request = RequestBuilder.newBuilder().paramEntry("user", username).paramEntry("password", pwd)
				.method("user.login").build();
		JSONObject response = zabbixApi.call(request);
		AuthResult result = JSONObject.toJavaObject(response, AuthResult.class);
		logger.trace("json �����{}", result);
		return result;
	}

}
