package com.zeus.controller;

import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.constant.Constants;
import com.zeus.common.constant.DiskRequestTypeEnum;
import com.zeus.common.constant.SnmpConstants;
import com.zeus.dto.DiskInfoDto;
import com.zeus.service.DiskMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * 获取磁盘剩余空间
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "diskAvailableSpace", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> diskAvailableSpace(HttpServletRequest request, HttpServletResponse response) {

        DiskInfoDto result = null;
        try {
            result = doCommon(request, SnmpConstants.SNMP_DISK_AVAILABLE_SPACE, DiskRequestTypeEnum.DISK_AVAILABLE_SPACE);
        } catch (Exception e) {
            logger.error("DiskMoitorController getDiskAvailSpace exception=" + e);
        }
        return resultOK(result);
    }

    /**
     * 获取磁盘使用空间
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "diskUsedSpace", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getDiskUsedSpace(HttpServletRequest request, HttpServletResponse response) {

        DiskInfoDto result = null;
        try {
            result = doCommon(request, SnmpConstants.SNMP_DISK_USED_SPACE, DiskRequestTypeEnum.DISK_USED_SPACE);
        } catch (Exception e) {
            logger.error("DiskMoitorController getDiskUsedSpace exception=" + e);
        }
        return resultOK(result);
    }

    /**
     * 获取磁盘总空间
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "diskTotalSpace", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getDiskTotalSpace(HttpServletRequest request, HttpServletResponse response) {

        DiskInfoDto result = null;
        try {
            result = doCommon(request, SnmpConstants.SNMP_DISK_TOTAL_SPACE, DiskRequestTypeEnum.DISK_TOTAL_SPACE);
        } catch (Exception e) {
            logger.error("DiskMoitorController getDiskTotalSpace exception=" + e);
        }
        return resultOK(result);
    }

    /**
     * 磁盘使用空间百分比
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "diskUsePercent", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getDiskUsePercent(HttpServletRequest request, HttpServletResponse response) {

        DiskInfoDto result = null;
        try {
            result = doCommon(request, SnmpConstants.SNMP_DISK_USE_PERCENT, DiskRequestTypeEnum.DISK_USE_PERCENT);
        } catch (Exception e) {
            logger.error("DiskMoitorController getDiskUsePercent exception=" + e);
        }
        return resultOK(result);
    }

    /**
     * 公共方法
     *
     * @param request
     * @return
     */
    public DiskInfoDto doCommon(HttpServletRequest request, String searchKey, DiskRequestTypeEnum requestTypeEnum) throws Exception {

        // 1.获取auth
        String auth = diskMonitorService.getAuth(request);

        // 2.获取指定主机的hostId
        String hostId = diskMonitorService.getHostId(apiCfg.getZabbixHostName(), auth);

        // 3.获取该指定主机的监控项itemId
        String itemId = diskMonitorService.getItemId(hostId, auth, searchKey);

        // 4.获取该监控项的监控数据
        DiskInfoDto diskInfoDto = diskMonitorService.getDiskMonitorInfo(itemId, auth, requestTypeEnum);

        return diskInfoDto;
    }
}
