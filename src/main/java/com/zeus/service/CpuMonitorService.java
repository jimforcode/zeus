package com.zeus.service;

import com.alibaba.fastjson.JSONArray;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public interface CpuMonitorService {
    String getAuth(HttpServletRequest request);

    String getHostId(String hostName, String auth);

    String getItemId(String hostId, String dataType, String auth);

    JSONArray getCpuMonitorInfo(String itemId, String timeFrom, String timeTill, String auth);
}
