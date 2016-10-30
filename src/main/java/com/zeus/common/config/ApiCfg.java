package com.zeus.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * �����ļ�
 * 
 * @author zengjin
 *
 */
@Component
public class ApiCfg {
	@Value("#{configProperties['zabbix.auth.url']}")
	private String zabbixAuthUrl;

	public String getZabbixAuthUrl() {
		return zabbixAuthUrl;
	}

	public void setZabbixAuthUrl(String zabbixAuthUrl) {
		this.zabbixAuthUrl = zabbixAuthUrl;
	}

	
	
}
