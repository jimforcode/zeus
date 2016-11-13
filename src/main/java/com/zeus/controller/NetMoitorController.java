package com.zeus.controller;

import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.requestEnum.MemoryRequestTypeEnum;
import com.zeus.common.requestEnum.NetRequestTypeEnum;
import com.zeus.dto.MemoryInfoDto;
import com.zeus.dto.NetInfoDto;
import com.zeus.service.MemoryMonitorService;
import com.zeus.service.NetMonitorService;
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
@RequestMapping("net")
public class NetMoitorController extends BaseController {

    @Autowired
    NetMonitorService netMonitorService;

    /**
     * 获取内存信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "netInfo", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getNetInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("hostName") String hostName, @RequestParam(defaultValue = "10", required = false) String limit) {

        List<NetInfoDto> result = null;
        try {
            result = doRequest(request, Arrays.asList(NetRequestTypeEnum.NET_ALL.getCode().split(",")), NetRequestTypeEnum.NET_ALL, hostName, Integer.valueOf(limit));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("NetMoitorController getNetInfo exception=" + e);
            return resultError(e.getLocalizedMessage());
        }
        return resultOK(result);
    }


    /**
     * 公共方法
     *
     * @param request
     * @return
     */
    public List<NetInfoDto> doRequest(HttpServletRequest request, List<String> searchKeyList, NetRequestTypeEnum requestTypeEnum, String hostName, Integer limit) throws Exception {

        // 参数检查
        isHostNameNull(hostName);

        // 1.获取auth
        String auth = netMonitorService.getAuth(request);

        // 2.获取指定主机的hostId
        String hostId = netMonitorService.getHostId(hostName, auth);

        // 3.获取该指定主机的监控项itemId
        Map<String, String> itemIdMap = netMonitorService.getItemId(hostId, auth, searchKeyList);

        // 4.获取该监控项的监控数据
        List<NetInfoDto> netInfoDtoList = netMonitorService.getNetMonitorInfo(itemIdMap, auth, requestTypeEnum, limit);

        return netInfoDtoList;
    }
}
