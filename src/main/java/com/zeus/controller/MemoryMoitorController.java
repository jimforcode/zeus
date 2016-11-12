package com.zeus.controller;

import com.alibaba.fastjson.JSONArray;
import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.config.ApiCfg;
import com.zeus.common.constant.Constants;
import com.zeus.common.utils.ZabbixUtil;
import com.zeus.service.MemoryMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
@Controller
@RequestMapping("memory")
public class MemoryMoitorController extends BaseController {

    @Autowired
    MemoryMonitorService memoryMonitorService;

    @RequestMapping(value = "memoryInfo", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getMemoryInfo(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam(value = "dataType", required = false, defaultValue = "snmp") String dataType) {

        JSONArray result = null;
        try {
            // 1.获取auth
            String auth = memoryMonitorService.getAuth(request);

            // 2.获取指定主机的hostId
            String hostId = memoryMonitorService.getHostId(apiCfg.getZabbixHostName(), auth);

            // 3.获取该指定主机的监控项itemId
            String itemId = memoryMonitorService.getItemId(hostId, dataType, auth);

            // 4.获取该监控项的监控数据
            result = memoryMonitorService.getMemoryMonitorInfo(itemId, null, null, auth);

        } catch (Exception e) {
            logger.error("MemoryMoitorController getMemoryInfo exception=" + e);
        }
        return resultOK(result);
    }
}
