package com.zeus.controller;

import com.alibaba.fastjson.JSONArray;
import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.utils.Constants;
import com.zeus.service.CpuMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/29 0029.
 */
@Controller
@RequestMapping("memory")
public class CpuMonitorController extends BaseController {

    @Autowired
    CpuMonitorService cpuDiskService;

    @RequestMapping(value = "cpuInfo", method = RequestMethod.GET)
    @ResponseBody
    @RequestAllowOirginRequired
    public Map<String, Object> zabbixApiDemo(HttpServletRequest request, HttpServletResponse response) {

        setResopnseAllowOrigin(request, response);

        // 1.获取auth
        String auth = cpuDiskService.getAuth(Constants.USER_NAME, Constants.PASS_WORD);

        // 2.获取指定主机的hostId
        String hostId = cpuDiskService.getHostId(Constants.HOST_NAME, auth);

        // 3.获取该指定主机的监控项itemId
        String itemId = cpuDiskService.getItemId(hostId, Constants.CPU_SYSTEM, auth);

        // 4.获取该监控项的监控数据
        JSONArray result = cpuDiskService.getCpuMonitorInfo(itemId, null, null, auth);

        return resultOK(result);
    }
}
