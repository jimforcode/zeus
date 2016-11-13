package com.zeus.service;

import com.zeus.common.requestEnum.MemoryRequestTypeEnum;
import com.zeus.dto.MemoryInfoDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public interface MemoryMonitorService {
    String getAuth(HttpServletRequest request);

    String getHostId(String hostName, String auth);

    Map<String, String> getItemId(String hostId, String auth, List<String> searchKeyList);

    List<MemoryInfoDto> getDiskMonitorInfo(Map<String, String> itemIdMap, String auth, MemoryRequestTypeEnum requestTypeEnum, Integer limit);
}
