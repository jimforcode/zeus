package com.zeus.controller;

import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.requestEnum.IORequestTypeEnum;
import com.zeus.common.requestEnum.MemoryRequestTypeEnum;
import com.zeus.dto.IOInfoDto;
import com.zeus.dto.MemoryInfoDto;
import com.zeus.service.IOMonitorService;
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
@RequestMapping("io")
public class IOMoitorController extends BaseController {

    @Autowired
    IOMonitorService ioMonitorService;

    /**
     * 获取IO信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "IOInfo", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getIoInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("hostName") String hostName, @RequestParam(defaultValue = "10", required = false) String limit) {

        List<IOInfoDto> result = null;
        try {
            result = doRequest(request, Arrays.asList(IORequestTypeEnum.IO_ALL.getCode().split(",")), IORequestTypeEnum.IO_ALL, hostName, Integer.valueOf(limit));
        } catch (Exception e) {
            logger.error("IOMoitorController getIoInfo exception=" + e);
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
    public List<IOInfoDto> doRequest(HttpServletRequest request, List<String> searchKeyList, IORequestTypeEnum requestTypeEnum, String hostName, Integer limit) throws Exception {

        // 参数检查
        isHostNameNull(hostName);

        // 1.获取auth
        String auth = ioMonitorService.getAuth(request);

        // 2.获取指定主机的hostId
        String hostId = ioMonitorService.getHostId(hostName, auth);

        // 3.获取该指定主机的监控项itemId
        Map<String, String> itemIdMap = ioMonitorService.getItemId(hostId, auth, searchKeyList);

        // 4.获取该监控项的监控数据
        List<IOInfoDto> memoryInfoDtoList = ioMonitorService.getIOMonitorInfo(itemIdMap, auth, requestTypeEnum, limit);

        return memoryInfoDtoList;
    }
}
