package com.zeus.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import io.github.hengyunabc.zabbix.api.DefaultZabbixApi;
import io.github.hengyunabc.zabbix.api.Request;
import io.github.hengyunabc.zabbix.api.RequestBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/10/23 0023.
 */
@Controller
public class ZabbixApiDemo {

    @RequestMapping(value = "zabbixDemo", method = RequestMethod.GET)
    @ResponseBody
    public void zabbixApiDemo(Model model) {

        String url = "http://139.196.50.175:81/zabbix/api_jsonrpc.php";
        DefaultZabbixApi zabbixApi = new DefaultZabbixApi(url);
        zabbixApi.init();

        boolean login = zabbixApi.login("Admin", "zabbix");
        System.err.println("login:" + login);

        String host = "139.196.50.175";
        JSONObject filter = new JSONObject();

        filter.put("host", new String[]{host});
        Request getRequest = RequestBuilder.newBuilder()
                .method("host.get").paramEntry("filter", filter)
                .build();
        JSONObject getResponse = zabbixApi.call(getRequest);
        System.err.println(getResponse);
        String hostid = getResponse.getJSONArray("result")
                .getJSONObject(0).getString("hostid");
        System.err.println(hostid);
    }

    @RequestMapping(value = "getAuth", method = RequestMethod.GET)
    @ResponseBody
    public String getAuth(Model model) {
        String url = "http://139.196.50.175:81/zabbix/api_jsonrpc.php";
        DefaultZabbixApi zabbixApi = new DefaultZabbixApi(url);
        zabbixApi.init();
        Request request = RequestBuilder.newBuilder().paramEntry("user", "Admin")
                .paramEntry("password", "zabbix").method("user.login").build();

        JSONObject response = zabbixApi.call(request);
        String auth = response.getString("result");
        if (auth != null && !auth.isEmpty()) {
            System.out.println("auth = [" + auth + "]");
        } else {
            System.out.println("No auth");
        }

       /* if (auth != null && !auth.isEmpty()) {
            JSONObject search = new JSONObject();
            search.put("key_", searchKey);
            Request request2 = RequestBuilder.newBuilder().method("item.get")
                    .paramEntry("output", "extend")
                    .paramEntry("host", hostName)
                    .paramEntry("search", search)
                    //.paramEntry("group", groupName)
                    .build();
            response = zabbixApi.call(request2);
        } else {
            return null;
        }*/
        return null;
    }

    @RequestMapping(value = "itemGet", method = RequestMethod.GET)
    @ResponseBody
    public void itemGet(Model model) {

        String url = "http://139.196.50.175:81/zabbix/api_jsonrpc.php";
        DefaultZabbixApi zabbixApi = new DefaultZabbixApi(url);
        zabbixApi.init();

//        boolean login = zabbixApi.login("Admin", "zabbix");
//        System.err.println("login:" + login);

        String host = "139.196.50.175";
        JSONObject params = new JSONObject();

        params.put("output", "extend");
        params.put("hostids", "10105");
        params.put("sortfield", "name");
        Request getRequest = RequestBuilder.newBuilder()
                .method("item.get").build();
        JSONObject getResponse = zabbixApi.call(getRequest);
        System.err.println(getResponse);

    }


}
