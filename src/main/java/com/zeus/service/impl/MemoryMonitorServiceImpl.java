package com.zeus.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zeus.service.CpuMonitorService;
import com.zeus.service.MemoryMonitorService;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 内存监控信息
 */
@Service
public class MemoryMonitorServiceImpl implements MemoryMonitorService {

    @Autowired
    private ZabbixApi zabbixApi;
    @Autowired
    private CpuMonitorService cpuMonitorService;

    @Override
    public String getAuth(String user, String password) {
        return cpuMonitorService.getAuth(user, password);
    }

    @Override
    public String getHostId(String hostName, String auth) {
        return cpuMonitorService.getHostId(hostName, auth);
    }

    @Override
    public String getItemId(String hostId, String searchKey, String auth) {
        return cpuMonitorService.getItemId(hostId, searchKey, auth);
    }

    @Override
    public JSONArray getMemoryMonitorInfo(String itemId, String timeFrom, String timeTill, String auth) {

        Request getRequest = RequestBuilder.newBuilder().paramEntry("history", "0")
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", "10").paramEntry("output", "extend").method("item.get")
                .auth(auth).build();
        JSONObject getResponse = zabbixApi.call(getRequest);

        JSONArray result = getResponse.getJSONArray("result");
        return result;
    }


}
