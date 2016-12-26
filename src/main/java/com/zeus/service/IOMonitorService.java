package com.zeus.service;

import com.zeus.common.requestEnum.IORequestTypeEnum;
import com.zeus.dto.IOInfoDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public interface IOMonitorService {
    String getAuth(HttpServletRequest request);

    String getHostId(String hostName, String auth);

    Map<String, String> getItemId(String hostId, String auth, List<String> searchKeyList);

    List<IOInfoDto> getIOMonitorInfo(Map<String, String> itemIdMap, String auth, IORequestTypeEnum requestTypeEnum, Integer limit);
}
