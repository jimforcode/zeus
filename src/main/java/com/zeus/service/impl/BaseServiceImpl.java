package com.zeus.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.utils.ZabbixUtil;
import com.zeus.dto.DiskInfoDto;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dingaolin252 on 2016/11/2.
 */
@Service
public class BaseServiceImpl {

    @Autowired
    public ZabbixApi zabbixApi;
    @Autowired
    public ZabbixUtil zabbixUtil;

    public String getAuth(HttpServletRequest request) {
        return zabbixUtil.getAuth(request);
    }

    public String getHostId(String hostName, String auth) {
        JSONObject filter = new JSONObject();
        filter.put("host", new String[]{hostName});

        Request request = RequestBuilder.newBuilder().paramEntry("filter", filter).method("host.get")
                .auth(auth).build();
        JSONObject response = zabbixApi.call(request);

        String hostId = response.getJSONArray("result")
                .getJSONObject(0).getString("hostid");
        return hostId;
    }

    public Map<String, String> getItemId(String hostId, String auth, List<String> searchKeyList) {
        Map<String, String> result = new HashMap<>();
        for (String searchKey : searchKeyList) {
            JSONObject filter = new JSONObject();
            filter.put("key_", new String[]{searchKey});

            Request request = RequestBuilder.newBuilder().paramEntry("search", filter)
                    .paramEntry("hostids", hostId).paramEntry("output", "itemids").method("item.get")
                    .auth(auth).build();
            JSONObject response = zabbixApi.call(request);

            String itemId = response.getJSONArray("result")
                    .getJSONObject(0).getString("itemid");
            result.put(searchKey, itemId);
        }
        return result;
    }


}
