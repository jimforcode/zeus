package com.zeus.service;

import com.alibaba.fastjson.JSONArray;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public interface CpuMonitorService {
    String getAuth(String user, String password);

    String getHostId(String hostName, String auth);

    String getItemId(String hostId, String searchKey, String auth);

    JSONArray getCpuMonitorInfo(String itemId, String timeFrom, String timeTill, String auth);
}
