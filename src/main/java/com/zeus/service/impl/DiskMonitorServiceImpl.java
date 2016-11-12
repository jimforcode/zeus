package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.constant.DiskRequestTypeEnum;
import com.zeus.dto.DiskInfoDto;
import com.zeus.dto.DtoBeanFactory;
import com.zeus.model.HistoryUint;
import com.zeus.service.DiskMonitorService;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 内存监控信息
 */
@Service
public class DiskMonitorServiceImpl extends BaseServiceImpl implements DiskMonitorService {

    @Autowired
    private ZabbixApi zabbixApi;

    @Override
    public DiskInfoDto getDiskMonitorInfo(String itemId, String auth, DiskRequestTypeEnum requestTypeEnum) {

        DiskInfoDto diskInfoDto = new DiskInfoDto();
        switch (requestTypeEnum) {
            case DISK_AVAILABLE_SPACE:
                diskInfoDto = getDiskAvailableSpace(itemId, auth);
                break;
            case DISK_USED_SPACE:
                diskInfoDto = getDiskUsedSpace(itemId, auth);
                break;
            case DISK_TOTAL_SPACE:
                diskInfoDto = getDiskTotalSpace(itemId, auth);
                break;
            case DISK_USE_PERCENT:
                diskInfoDto = getDiskUsePercent(itemId, auth);
                break;
            default:
                break;
        }
        return diskInfoDto;
    }

    private DiskInfoDto getDiskUsePercent(String itemId, String auth) {
        String jsonResult = doRequestCommon(itemId, auth);
        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }
        return DtoBeanFactory.convertByDiskUsePercent(historyUints.get(0));
    }

    private DiskInfoDto getDiskTotalSpace(String itemId, String auth) {
        String jsonResult = doRequestCommon(itemId, auth);
        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }
        return DtoBeanFactory.convertByDiskTotalSpace(historyUints.get(0));
    }

    private DiskInfoDto getDiskAvailableSpace(String itemId, String auth) {
        String jsonResult = doRequestCommon(itemId, auth);
        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }
        return DtoBeanFactory.convertByDiskAvailableSpace(historyUints.get(0));
    }

    private DiskInfoDto getDiskUsedSpace(String itemId, String auth) {
        String jsonResult = doRequestCommon(itemId, auth);
        List<HistoryUint> historyUints = JSONObject.parseArray(jsonResult, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }
        return DtoBeanFactory.convertByDiskUsedSpace(historyUints.get(0));
    }

    private String doRequestCommon(String itemId, String auth) {
        Request getRequest = RequestBuilder.newBuilder()
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", "1").paramEntry("history", "3").paramEntry("output", "extend").method("history.get")
                .auth(auth).build();

        JSONObject response = zabbixApi.call(getRequest);
        return response.getJSONArray("result").toString();
    }

}
