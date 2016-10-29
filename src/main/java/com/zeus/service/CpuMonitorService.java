package com.zeus.service;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public interface CpuMonitorService {
    public String getAuth(String zabbixServerUrl, String user, String password);

    public String getHostId(String zabbixServerUrl, String hostName, String auth);

    public String getItemId(String zabbixServerUrl, String hostId, String searchKey, String auth);

    public JSONArray getCpuMonitorInfo(String zabbixServerUrl, String itemId, String timeFrom, String timeTill, String auth);
}
