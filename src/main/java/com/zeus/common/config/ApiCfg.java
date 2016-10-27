package com.zeus.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * ≈‰÷√Œƒº˛
 * 
 * @author zengjin
 *
 */
@Component
public class ApiCfg {
	@Value("#{configProperties['name']}")
	private String name;
	@Value("#{configProperties['zabbix.host']}")
	private String zabbixHost;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZabbixHost() {
		return zabbixHost;
	}

	public void setZabbixHost(String zabbixHost) {
		this.zabbixHost = zabbixHost;
	}

}
