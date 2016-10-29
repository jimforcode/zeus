package com.zeus.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zeus.service.CpuMonitorService;
import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import org.springframework.stereotype.Service;

/**
 * CPU监控信息
 */
@Service
public class CpuMonitorServiceImpl implements CpuMonitorService {

    @Override
    public String getAuth(String zabbixServerUrl, String user, String password) {
        DefaultZabbixApi zabbixApi = new DefaultZabbixApi(zabbixServerUrl);
        zabbixApi.init();
        Request request = RequestBuilder.newBuilder().paramEntry("user", user)
                .paramEntry("password", password).method("user.login").build();

        JSONObject response = zabbixApi.call(request);
        return response.getString("result");
    }

    @Override
    public String getHostId(String zabbixServerUrl, String hostName, String auth) {
        DefaultZabbixApi zabbixApi = new DefaultZabbixApi(zabbixServerUrl);
        zabbixApi.init();

        JSONObject filter = new JSONObject();
        filter.put("host", new String[]{hostName});

        Request getRequest = RequestBuilder.newBuilder().paramEntry("filter", filter).method("host.get")
                .auth(auth).build();
        JSONObject getResponse = zabbixApi.call(getRequest);

        String hostId = getResponse.getJSONArray("result")
                .getJSONObject(0).getString("hostid");
        return hostId;
    }

    @Override
    public String getItemId(String zabbixServerUrl, String hostId, String searchKey, String auth) {
        DefaultZabbixApi zabbixApi = new DefaultZabbixApi(zabbixServerUrl);
        zabbixApi.init();

        JSONObject filter = new JSONObject();
        filter.put("key_", new String[]{searchKey});

        Request getRequest = RequestBuilder.newBuilder().paramEntry("search", filter)
                .paramEntry("hostids", hostId).paramEntry("output", "itemids").method("item.get")
                .auth(auth).build();
        JSONObject getResponse = zabbixApi.call(getRequest);

        String itemId = getResponse.getJSONArray("result")
                .getJSONObject(0).getString("itemid");
        return itemId;
    }

    @Override
    public JSONArray getCpuMonitorInfo(String zabbixServerUrl, String itemId, String timeFrom, String timeTill, String auth) {
        DefaultZabbixApi zabbixApi = new DefaultZabbixApi(zabbixServerUrl);
        zabbixApi.init();

        Request getRequest = RequestBuilder.newBuilder().paramEntry("history", "0")
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", "10").paramEntry("output", "extend").method("history.get")
                .auth(auth).build();
        JSONObject getResponse = zabbixApi.call(getRequest);

        JSONArray result = getResponse.getJSONArray("result");
        return result;
    }


}
