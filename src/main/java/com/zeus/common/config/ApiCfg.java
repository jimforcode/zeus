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

	@Value("#{configProperties['zabbix.user']}")
	private String zabbixUser;

	@Value("#{configProperties['zabbix.password']}")
	private String zabbixPassword;

	@Value("#{configProperties['zabbix.host']}")
	private String zabbixHost;

	@Value("#{configProperties['zabbix.host.port']}")
	private String zabbixHostPort;

	@Value("#{configProperties['zabbix.password']}")

	public String getZabbixAuthUrl() {
		return zabbixAuthUrl;
	}

	public void setZabbixAuthUrl(String zabbixAuthUrl) {
		this.zabbixAuthUrl = zabbixAuthUrl;
	}

	public String getZabbixUser() {
		return zabbixUser;
	}

	public void setZabbixUser(String zabbixUser) {
		this.zabbixUser = zabbixUser;
	}

	public String getZabbixPassword() {
		return zabbixPassword;
	}

	public void setZabbixPassword(String zabbixPassword) {
		this.zabbixPassword = zabbixPassword;
	}

	public String getZabbixHost() {
		return zabbixHost;
	}

	public void setZabbixHost(String zabbixHost) {
		this.zabbixHost = zabbixHost;
	}

	public String getZabbixHostPort() {
		return zabbixHostPort;
	}

	public void setZabbixHostPort(String zabbixHostPort) {
		this.zabbixHostPort = zabbixHostPort;
	}

}
