package com.zeus.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zeus.common.constant.AgentConstants;
import com.zeus.common.constant.DataTypeEnum;
import com.zeus.common.constant.SnmpConstants;
import com.zeus.service.MemoryMonitorService;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 内存监控信息
 */
@Service
public class MemoryMonitorServiceImpl extends BaseServiceImpl implements MemoryMonitorService {

    @Autowired
    private ZabbixApi zabbixApi;

    @Override
    public String getItemId(String hostId, String dataType, String auth) {
        JSONObject filter = new JSONObject();
        String searchKey = SnmpConstants.SNMP_MEMORY_SYSTEM;

        if (DataTypeEnum.AGENT.getCode().equals(dataType)) {
            searchKey = AgentConstants.AGENT_CPU_SYSTEM;
        }
        filter.put("key_", new String[]{searchKey});
        return super.getItemId(hostId, auth, filter);
    }

    @Override
    public JSONArray getMemoryMonitorInfo(String itemId, String timeFrom, String timeTill, String auth) {

        Request getRequest = RequestBuilder.newBuilder().paramEntry("history", "0")
                .paramEntry("itemids", itemId).paramEntry("sortfield", "clock").paramEntry("sortorder", "DESC")
                .paramEntry("limit", "10").paramEntry("output", "extend").method("item.get")
                .auth(auth).build();
        JSONObject getResponse = zabbixApi.call(getRequest);

        JSONArray result = getResponse.getJSONArray("result");
        return result;
    }


}
