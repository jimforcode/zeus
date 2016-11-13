package com.zeus.service;

import com.zeus.common.requestEnum.DiskRequestTypeEnum;
import com.zeus.dto.DiskInfoDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
public interface DiskMonitorService {
    String getAuth(HttpServletRequest request);

    String getHostId(String hostName, String auth);

    Map<String, String> getItemId(String hostId, String auth, List<String> searchKeyList);

    List<DiskInfoDto> getDiskMonitorInfo(Map<String, String> itemId, String auth, DiskRequestTypeEnum requestTypeEnum);
}
