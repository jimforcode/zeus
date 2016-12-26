package com.zeus.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeus.common.annotation.login.RequestAllowOirginRequired;
import com.zeus.dto.LocalHostResDto;
import com.zeus.dto.ZeusHostGetReqDto;
import com.zeus.dto.ZeusHostGetResDto;
import com.zeus.dto.ZeusHostSaveReqDto;
import com.zeus.dto.ZeusHostSaveResDto;
import com.zeus.service.ZeusHostService;


@Controller
@RequestMapping("zeusHost")
public class ZeusHostController extends BaseController {

    @Autowired
    ZeusHostService zeusHostService;

    /**
     * 获取局域网主机
     * 
     */
    @RequestMapping(value = "getLocalHost", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getLocalHost(HttpServletRequest request,
    		HttpServletResponse response) {

        LocalHostResDto localHostResDto = null;
        try {
        	localHostResDto = zeusHostService.getLocalHost();
        } catch (Exception e) {
            logger.error("ZeusHostController getLocalHost exception：" + e);
            return resultError(e.getLocalizedMessage());
        }
        return resultOK(localHostResDto);
    }

    /**
     * 保存主机
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "saveZeusHost", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> saveZeusHost(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam("zeusHostSaveReqDto") ZeusHostSaveReqDto zeusHostSaveReqDto) {

    	ZeusHostSaveResDto zeusHostSaveResDto = new ZeusHostSaveResDto();
        try {
        	zeusHostSaveResDto = zeusHostService.saveZeusHost(zeusHostSaveReqDto);
        } catch (Exception e) {
            logger.error("ZeusHostController saveZeusHost exception：" + e);
            return resultError(e.getLocalizedMessage());
        }
        return resultOK(zeusHostSaveResDto);
    }
    
    /**
     * 获取主机
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "getZeusHost", method = RequestMethod.GET)
    @RequestAllowOirginRequired
    @ResponseBody
    public Map<String, Object> getZeusHost(HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam("zeusHostGetReqDto") ZeusHostGetReqDto zeusHostGetReqDto) {

    	ZeusHostGetResDto zeusHostGetResDto = new ZeusHostGetResDto();
        try {
        	zeusHostGetResDto = zeusHostService.getZeusHost(zeusHostGetReqDto);
        } catch (Exception e) {
            logger.error("ZeusHostController getZeusHost exception：" + e);
            return resultError(e.getLocalizedMessage());
        }
        return resultOK(zeusHostGetResDto);
    }

}
