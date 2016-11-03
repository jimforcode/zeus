package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.utils.ZabbixUtil;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dingaolin252 on 2016/11/2.
 */
@Service
public class BaseServiceImpl {

    @Autowired
    public ZabbixApi zabbixApi;
    @Autowired
    public ZabbixUtil zabbixUtil;

    public String getAuth(HttpServletRequest request) {
        return zabbixUtil.getAuth(request);
    }

    public String getHostId(String hostName, String auth) {
        JSONObject filter = new JSONObject();
        filter.put("host", new String[]{hostName});

        Request getRequest = RequestBuilder.newBuilder().paramEntry("filter", filter).method("host.get")
                .auth(auth).build();
        JSONObject getResponse = zabbixApi.call(getRequest);

        String hostId = getResponse.getJSONArray("result")
                .getJSONObject(0).getString("hostid");
        return hostId;
    }

    public String getItemId(String hostId, String auth, JSONObject filter) {

        Request getRequest = RequestBuilder.newBuilder().paramEntry("search", filter)
                .paramEntry("hostids", hostId).paramEntry("output", "itemids").method("item.get")
                .auth(auth).build();
        JSONObject getResponse = zabbixApi.call(getRequest);

        String itemId = getResponse.getJSONArray("result")
                .getJSONObject(0).getString("itemid");
        return itemId;
    }

}
