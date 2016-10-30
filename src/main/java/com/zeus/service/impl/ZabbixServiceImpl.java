package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeus.service.ZabbixService;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZabbixServiceImpl implements ZabbixService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ZabbixApi zabbixApi;

    /**
     * 认证
     */
    @Override
    public boolean isLogin(String username, String pwd) {
        boolean result = this.zabbixApi.login(username, pwd);
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
        return getResponse;
    }

}
