package com.zeus.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zeus.common.constant.AgentConstants;
import com.zeus.common.constant.DataTypeEnum;
import com.zeus.common.constant.SnmpConstants;
import com.zeus.service.CpuMonitorService;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CPU监控信息
 */
@Service
public class CpuMonitorServiceImpl extends BaseServiceImpl implements CpuMonitorService {

    @Autowired
    private ZabbixApi zabbixApi;

    @Override
    public String getItemId(String hostId, String dataType, String auth) {

        String searchKey = SnmpConstants.SNMP_CPU_SYSTEM;

        if (DataTypeEnum.AGENT.getCode().equals(dataType)) {
            searchKey = AgentConstants.AGENT_CPU_SYSTEM;
        }
        return super.getItemId(hostId, auth,searchKey);
    }

    @Override
    public JSONArray getCpuMonitorInfo(String itemId, String timeFrom, String timeTill, String auth) {

        Request getRequest = RequestBuilder.newBuilder().paramEntry("history", "0")
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", "10").paramEntry("output", "extend").method("history.get")
                .auth(auth).build();
        JSONObject getResponse = zabbixApi.call(getRequest);

        JSONArray result = getResponse.getJSONArray("result");
        return result;
    }
}
