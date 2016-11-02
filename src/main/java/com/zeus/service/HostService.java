package com.zeus.service;

import com.alibaba.fastjson.JSONObject;

public interface HostService {
	boolean isExist(String host);

	public JSONObject hostGet(String[] hosts);
}
