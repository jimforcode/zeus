package com.zeus.controller;

import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.requestEnum.CpuRequestTypeEnum;
import com.zeus.common.requestEnum.DiskRequestTypeEnum;
import com.zeus.dto.CpuInfoDto;
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
import java.util.Arrays;
import java.util.List;
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
     * 获取磁盘信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "diskInfo", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getDiskInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("hostName") String hostName) {

        List<DiskInfoDto> result = null;
        try {
            result = doRequest(request, Arrays.asList(DiskRequestTypeEnum.DISK_ALL.getCode().split(",")), DiskRequestTypeEnum.DISK_ALL, hostName);
        } catch (Exception e) {
            logger.error("DiskMoitorController getDiskInfo exception=" + e);
        }
        return resultOK(result);
    }


    /**
     * 公共方法
     *
     * @param request
     * @return
     */
    public List<DiskInfoDto> doRequest(HttpServletRequest request, List<String> searchKeyList, DiskRequestTypeEnum requestTypeEnum, String hostName) throws Exception {

        // 1.获取auth
        String auth = diskMonitorService.getAuth(request);

        // 2.获取指定主机的hostId
        String hostId = diskMonitorService.getHostId(hostName, auth);

        // 3.获取该指定主机的监控项itemId
        Map<String, String> itemIdMap = diskMonitorService.getItemId(hostId, auth, searchKeyList);

        // 4.获取该监控项的监控数据
        List<DiskInfoDto> diskInfoDtoList = diskMonitorService.getDiskMonitorInfo(itemIdMap, auth, requestTypeEnum);

        return diskInfoDtoList;
    }
}
