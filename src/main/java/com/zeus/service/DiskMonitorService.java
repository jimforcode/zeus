package com.zeus.service;

import com.zeus.dto.DiskInfoDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public interface DiskMonitorService {
    String getAuth(HttpServletRequest request);

    String getHostId(String hostName, String auth);

    String getItemId(String hostId, String searchKey, String auth);

    DiskInfoDto getDiskMonitorInfo(String itemId, String auth);
}
