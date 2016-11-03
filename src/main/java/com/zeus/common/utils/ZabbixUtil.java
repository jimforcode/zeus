package com.zeus.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.zeus.common.config.ApiCfg;
import com.zeus.common.exception.AuthException;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import io.github.hengyunabc.zabbix.api.ZabbixApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dingaolin on 2016/11/2.
 * ZabbixUtil 工具类
 */
@Component
public class ZabbixUtil {

    @Autowired
    public ZabbixApi zabbixApi;
    @Autowired
    public ApiCfg apiCfg;

    /**
     * 获取 auth
     *
     * @param request
     * @return
     */
    public String getAuth(HttpServletRequest request) throws AuthException{

        String auth = (String) request.getSession().getAttribute("auth");
        if (StringUtils.isEmpty(auth)) {
            Request zabbixRequest = RequestBuilder.newBuilder().paramEntry("user", this.apiCfg.getZabbixUserName())
                    .paramEntry("password", this.apiCfg.getZabbixPassWord()).method("user.login").build();
            JSONObject response = zabbixApi.call(zabbixRequest);
            if (response == null) {
                throw new AuthException("ZabbixUtil ZabbixUtil response is null");
            }

            auth = response.getString("result");
            request.getSession().setAttribute("auth", auth);
        }
        return auth;
    }

}