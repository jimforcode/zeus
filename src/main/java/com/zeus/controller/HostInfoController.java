package com.zeus.controller;

import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.requestEnum.HostInfoDetailEnum;
import com.zeus.dto.HostInfoDetailDto;
import com.zeus.service.HostInfoMonitorService;
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
@RequestMapping("hostInfo")
public class HostInfoController extends BaseController {

    @Autowired
    HostInfoMonitorService hostInfoMonitorService;

    /**
     * 获取主机信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getHostInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("hostName") String hostName) {

        HostInfoDetailDto result = null;
        try {
            result = doRequest(request, Arrays.asList(HostInfoDetailEnum.HOST_SYS_NAME.getCode().split(",")), HostInfoDetailEnum.HOST_SYS_NAME, hostName);
        } catch (Exception e) {
            logger.error("HostInfoController getHostInfo exception=" + e);
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
    public HostInfoDetailDto doRequest(HttpServletRequest request, List<String> searchKeyList, HostInfoDetailEnum requestTypeEnum, String hostName) throws Exception {

        // 参数检查
        isHostNameNull(hostName);

        // 1.获取auth
        String auth = hostInfoMonitorService.getAuth(request);

        // 2.获取指定主机的hostId
        String hostId = hostInfoMonitorService.getHostId(hostName, auth);

        // 3.获取该指定主机的监控项itemId
        Map<String, String> itemIdMap = hostInfoMonitorService.getItemId(hostId, auth, searchKeyList);

        // 4.获取该监控项的监控数据
        HostInfoDetailDto hostInfoDetailDtoList = hostInfoMonitorService.getHostInfoDetail(itemIdMap, auth, requestTypeEnum);

        return hostInfoDetailDtoList;
    }

}
