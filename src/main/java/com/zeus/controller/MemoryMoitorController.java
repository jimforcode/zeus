package com.zeus.controller;

import com.alibaba.fastjson.JSONArray;
import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.common.utils.Constants;
import com.zeus.service.MemoryMonitorService;
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
@RequestMapping("memory")
public class MemoryMoitorController extends BaseController {

    @Autowired
    MemoryMonitorService memoryMonitorService;

    @RequestMapping(value = "memoryInfo", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getMemoryInfo(HttpServletRequest request, HttpServletResponse response) {

        // 1.获取auth
        String auth = (String) request.getSession().getAttribute("auth");
        if (auth == null) {
            auth = memoryMonitorService.getAuth(Constants.USER_NAME, Constants.PASS_WORD);
            request.getSession().setAttribute("auth", auth);
        }

        // 2.获取指定主机的hostId
        String hostId = memoryMonitorService.getHostId(Constants.HOST_NAME, auth);

        // 3.获取该指定主机的监控项itemId
        String itemId = memoryMonitorService.getItemId(hostId, Constants.MEMORY_SYSTEM, auth);

        // 4.获取该监控项的监控数据
        JSONArray result = memoryMonitorService.getMemoryMonitorInfo(itemId, null, null, auth);

        return resultOK(result);
    }
}
