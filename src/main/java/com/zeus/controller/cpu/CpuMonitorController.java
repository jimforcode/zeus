package com.zeus.controller.cpu;

import com.alibaba.fastjson.JSONArray;
import com.zeus.controller.BaseController;
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
    public Map<String, Object> zabbixApiDemo(HttpServletRequest request, HttpServletResponse response) {

        // TODO
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed","1");

        // TODO
        String zabbixServerUrl = "http://139.196.50.175:81/zabbix/api_jsonrpc.php";
        String hostName = "139.196.50.175";
        String searchKey = "system.cpu.util[,system]";

        // 1.获取auth
        String auth = cpuDiskService.getAuth(zabbixServerUrl, "Admin", "zabbix");

        // 2.获取指定主机的hostId
        String hostId = cpuDiskService.getHostId(zabbixServerUrl, hostName, auth);

        // 3.获取该指定主机的监控项itemId
        String itemId = cpuDiskService.getItemId(zabbixServerUrl, hostId, searchKey, auth);

        // 4.获取该监控项的监控数据
        JSONArray result = cpuDiskService.getCpuMonitorInfo(zabbixServerUrl, itemId, null, null, auth);

        return resultOK(result);
    }
}
