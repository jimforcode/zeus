package com.zeus.zabbix;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;

@Component
public class ZabbixDemo {

	public String demo() {
		String url = "http://139.196.50.175:81/zabbix/api_jsonrpc.php";
		DefaultZabbixApi zabbixApi = new DefaultZabbixApi(url);
		zabbixApi.init();
		Request request = RequestBuilder.newBuilder().paramEntry("user", "Admin").paramEntry("password", "zabbix")
				.method("user.login").build();

		JSONObject response = zabbixApi.call(request);
		String auth = response.getString("result");
		return auth;
	}
}
