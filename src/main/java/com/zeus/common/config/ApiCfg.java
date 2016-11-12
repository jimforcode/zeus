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

	@Value("#{configProperties['zabbix.username']}")
	private String zabbixUserName;

	@Value("#{configProperties['zabbix.password']}")
	private String zabbixPassWord;

	@Value("#{configProperties['zabbix.hostname']}")
	private String zabbixHostName;

	public String getZabbixHostName() {
		return zabbixHostName;
	}

	public void setZabbixHostName(String zabbixHostName) {
		this.zabbixHostName = zabbixHostName;
	}

	public String getZabbixAuthUrl() {
		return zabbixAuthUrl;
	}

	public void setZabbixAuthUrl(String zabbixAuthUrl) {
		this.zabbixAuthUrl = zabbixAuthUrl;
	}

	public String getZabbixUserName() {
		return zabbixUserName;
	}

	public void setZabbixUserName(String zabbixUserName) {
		this.zabbixUserName = zabbixUserName;
	}

	public String getZabbixPassWord() {
		return zabbixPassWord;
	}

	public void setZabbixPassWord(String zabbixPassWord) {
		this.zabbixPassWord = zabbixPassWord;
	}
}
