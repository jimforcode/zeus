package com.zeus.controller;

import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.requestEnum.CpuRequestTypeEnum;
import com.zeus.dto.CpuInfoDto;
import com.zeus.service.CpuMonitorService;
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
 * Created by Administrator on 2016/10/29 0029.
 */
@Controller
@RequestMapping("cpu")
public class CpuMonitorController extends BaseController {

    @Autowired
    CpuMonitorService cpuMonitorService;

    /**
     * 获取CPU信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "cpuInfo", method = RequestMethod.GET)
    @ResponseBody
    @RequestAllowOirginRequired
    public Map<String, Object> getCpuInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("hostName") String hostName, @RequestParam(defaultValue = "10", required = false) String limit) {
        List<CpuInfoDto> result = null;
        try {
            result = doRequest(request, Arrays.asList(CpuRequestTypeEnum.CPU_ALL.getCode().split(",")), CpuRequestTypeEnum.CPU_ALL, hostName, Integer.valueOf(limit));
        } catch (Exception e) {
            logger.error("CpuMonitorController getCpuInfo exception=" + e);
        }
        return resultOK(result);
    }

    /**
     * 公共方法
     *
     * @param request
     * @return
     */
    private List<CpuInfoDto> doRequest(HttpServletRequest request, List<String> searchKeyList, CpuRequestTypeEnum requestTypeEnum, String hostName, Integer limit) throws Exception {

        // 1.获取auth
        String auth = cpuMonitorService.getAuth(request);

        // 2.获取指定主机的hostId
        String hostId = cpuMonitorService.getHostId(hostName, auth);

        // 3.获取该指定主机的监控项itemIdList
        Map<String, String> itemIdMap = cpuMonitorService.getItemId(hostId, auth, searchKeyList);

        // 4.获取该监控项的监控数据
        List<CpuInfoDto> cpuInfoDtoList = cpuMonitorService.getCpuMonitorInfo(itemIdMap, null, null, auth, requestTypeEnum, limit);

        return cpuInfoDtoList;
    }
}
