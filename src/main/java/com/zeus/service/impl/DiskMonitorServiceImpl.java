package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
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
    public DiskInfoDto getDiskMonitorInfo(String itemId, String auth) {
        Request getRequest = RequestBuilder.newBuilder()
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", "10").paramEntry("history", "3").paramEntry("output", "extend").method("history.get")
                .auth(auth).build();

        JSONObject response = zabbixApi.call(getRequest);
        String jsonArray = response.getJSONArray("result").toString();

        List<HistoryUint> historyUints = JSONObject.parseArray(jsonArray, HistoryUint.class);
        if (CollectionUtils.isEmpty(historyUints)) {
            return null;
        }

        return DtoBeanFactory.convertByDiskAvailSpace(historyUints.get(0));
    }

}
