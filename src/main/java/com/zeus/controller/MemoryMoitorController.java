package com.zeus.controller;

import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.requestEnum.MemoryRequestTypeEnum;
import com.zeus.dto.DiskInfoDto;
import com.zeus.dto.MemoryInfoDto;
import com.zeus.service.MemoryMonitorService;
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
@RequestMapping("memory")
public class MemoryMoitorController extends BaseController {

    @Autowired
    MemoryMonitorService memoryMonitorService;

    /**
     * 获取内存信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "memoryInfo", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getMemoryInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("hostName") String hostName) {

        List<MemoryInfoDto> result = null;
        try {
            result = doRequest(request, Arrays.asList(MemoryRequestTypeEnum.MEMORY_ALL.getCode().split(",")), MemoryRequestTypeEnum.MEMORY_ALL, hostName);
        } catch (Exception e) {
            logger.error("MemoryMoitorController getMemoryInfo exception=" + e);
        }
        return resultOK(result);
    }


    /**
     * 公共方法
     *
     * @param request
     * @return
     */
    public List<MemoryInfoDto> doRequest(HttpServletRequest request, List<String> searchKeyList, MemoryRequestTypeEnum requestTypeEnum, String hostName) throws Exception {

        // 1.获取auth
        String auth = memoryMonitorService.getAuth(request);

        // 2.获取指定主机的hostId
        String hostId = memoryMonitorService.getHostId(hostName, auth);

        // 3.获取该指定主机的监控项itemId
        Map<String, String> itemIdMap = memoryMonitorService.getItemId(hostId, auth, searchKeyList);

        // 4.获取该监控项的监控数据
        List<MemoryInfoDto> memoryInfoDtoList = memoryMonitorService.getDiskMonitorInfo(itemIdMap, auth, requestTypeEnum);

        return memoryInfoDtoList;
    }
}
