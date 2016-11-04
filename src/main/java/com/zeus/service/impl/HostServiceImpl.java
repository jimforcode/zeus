package com.zeus.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zeus.service.HostService;

import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;

@Service
public class HostServiceImpl implements HostService {
	private static final Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);
	@Autowired
	ZabbixApi zabbixApi;

	@Override
	public boolean isExist(String host) {
		Request getRequest = RequestBuilder.newBuilder().paramEntry("host", host).method("host.exists").build();
		JSONObject getResponse = zabbixApi.call(getRequest);
		logger.trace("host.get:{}", getResponse);
		return false;
	}

	@Override
	public JSONObject hostGet(String[] hosts) {
		logger.trace("host.get:input:{}", hosts);
		JSONObject filter = new JSONObject();
		filter.put("host", hosts);
		Request getRequest = RequestBuilder.newBuilder().method("host.get").paramEntry("filter", filter).build();
		JSONObject getResponse = zabbixApi.call(getRequest);
		logger.trace("host.get:output{}", getResponse);
		return getResponse;
	}

	@Override
	public JSONObject hostGetWithselectGroups(String[] hosts) {
		logger.trace("host.get:input:{}", hosts);
		JSONObject filter = new JSONObject();
		filter.put("host", hosts);
		Request getRequest = RequestBuilder.newBuilder().method("host.get").paramEntry("filter", filter).paramEntry("selectGroups", "extend").paramEntry("output", "hostid").build();
		JSONObject getResponse = zabbixApi.call(getRequest);
		logger.trace("host.get:output{}", getResponse);
		return getResponse;
	}

}
