package com.zeus.service;

import com.zeus.common.requestEnum.NetRequestTypeEnum;
import com.zeus.dto.NetInfoDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public interface NetMonitorService {
    String getAuth(HttpServletRequest request);

    String getHostId(String hostName, String auth);

    Map<String, String> getItemId(String hostId, String auth, List<String> searchKeyList);

    List<NetInfoDto> getNetMonitorInfo(Map<String, String> itemIdMap, String auth, NetRequestTypeEnum requestTypeEnum, Integer limit);
}
