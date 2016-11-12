package com.zeus.controller;

import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.constant.Constants;
import com.zeus.common.constant.SnmpConstants;
import com.zeus.common.utils.ZabbixUtil;
import com.zeus.dto.DiskInfoDto;
import com.zeus.service.DiskMonitorService;
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
@RequestMapping("disk")
public class DiskMoitorController extends BaseController {

    @Autowired
    DiskMonitorService diskMonitorService;
    @Autowired
    ZabbixUtil zabbixUtil;

    @RequestMapping(value = "diskAvailSpace", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getDiskInfo(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam(value = "dataType", required = false, defaultValue = "snmp") String dataType) {

        DiskInfoDto result = null;
        try {
            // 1.获取auth
            String auth = diskMonitorService.getAuth(request);

            // 2.获取指定主机的hostId
            String hostId = diskMonitorService.getHostId(Constants.HOST_NAME, auth);

            // 3.获取该指定主机的监控项itemId
            String itemId = diskMonitorService.getItemId(hostId, auth, SnmpConstants.SNMP_DISK_AVAIL);

            // 4.获取该监控项的监控数据
            result = diskMonitorService.getDiskMonitorInfo(itemId, auth);

        } catch (Exception e) {
            logger.error("MemoryMoitorController getMemoryInfo exception=" + e);
        }
        return resultOK(result);
    }
}
