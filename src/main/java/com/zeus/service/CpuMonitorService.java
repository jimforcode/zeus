package com.zeus.service;

import com.zeus.common.requestEnum.CpuRequestTypeEnum;
import com.zeus.dto.CpuInfoDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public interface CpuMonitorService {
    String getAuth(HttpServletRequest request);

    String getHostId(String hostName, String auth);

    Map<String, String> getItemId(String hostId, String auth, List<String> searchKeyList);

    List<CpuInfoDto> getCpuMonitorInfo(Map<String, String> itemIdMap, String timeFrom, String timeTill, String auth, CpuRequestTypeEnum requestTypeEnum);
}
