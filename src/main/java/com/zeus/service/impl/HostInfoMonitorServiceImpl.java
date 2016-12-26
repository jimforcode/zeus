package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.requestEnum.HostInfoDetailEnum;
import com.zeus.dto.HostInfoDetailDto;
import com.zeus.model.HistoryText;
import com.zeus.service.HostInfoMonitorService;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 内存监控信息
 */
@Service
public class HostInfoMonitorServiceImpl extends BaseServiceImpl implements HostInfoMonitorService {

    @Autowired
    private ZabbixApi zabbixApi;

    @Override
    public HostInfoDetailDto getHostInfoDetail(Map<String, String> itemIdMap, String auth, HostInfoDetailEnum hostInfoDetailEnum) {
        HostInfoDetailDto hostInfoDetailDto = new HostInfoDetailDto();
        switch (hostInfoDetailEnum) {
            case HOST_ALL:
//                hostInfoDetailDto = getAll(itemIdMap, auth);
                break;
            case HOST_SYS_NAME:
                hostInfoDetailDto = getHostSysName(itemIdMap, auth);
                break;
            default:
                break;
        }
        return hostInfoDetailDto;
    }

    private HostInfoDetailDto getHostSysName(Map<String, String> itemIdMap, String auth) {
        HostInfoDetailDto hostInfoDetailDto = new HostInfoDetailDto();
        String itemId = itemIdMap.get(HostInfoDetailEnum.HOST_SYS_NAME.getCode());

        String jsonResult = doRequestCommon(itemId, auth);
        List<HistoryText> historyText = JSONObject.parseArray(jsonResult, HistoryText.class);

        hostInfoDetailDto.setHostName(historyText.get(0).getValue());
        return hostInfoDetailDto;

    }

    private String doRequestCommon(String itemId, String auth) {
        Request getRequest = RequestBuilder.newBuilder()
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", 1).paramEntry("history", "4").paramEntry("output", "extend").method("history.get")
                .auth(auth).build();

        JSONObject response = zabbixApi.call(getRequest);
        return response.getJSONArray("result").toString();
    }

}
