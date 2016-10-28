package com.zeus.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;



/**
 * @author java_guoenzhou@163.com
 *
 */
public class ZabbixUtil {

	/**
	 * HTTP 请求
	 * 
	 * @param jsonArgument
	 * @return
	 */
	public static String get_data(String jsonArgument) {
		try {
			URL httpUrl = new URL("http://localhost:8089/zeus/demo/get");
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json-rpc");
			// 表单参数
			StringBuffer params = new StringBuffer();
			params.append(jsonArgument);
			byte[] bypes = params.toString().getBytes();
			conn.getOutputStream().write(bypes);// 输入参数
			// 返回
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = reader.readLine();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		// String result = execute("hostgroup.create",new String[]{"TEST3"});
		// System.out.println(result);
        System.out.println(get_data("{name=jim&age=20}"));;
	}
}