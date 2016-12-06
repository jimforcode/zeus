package com.zeus.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zeus.common.config.ApiCfg;

import io.github.hengyunabc.zabbix.api.ZabbixApi;
@Component
public class AutoAuth implements InitializingBean {

	@Autowired
	private ApiCfg apiCfg;

	@Autowired
	private ZabbixApi zabbixApi;

	private static Logger log = LoggerFactory.getLogger(AutoAuth.class);

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("AutoAuth physical address:{}", this);
		boolean login = zabbixApi.login(apiCfg.getZabbixUser(), apiCfg.getZabbixPassword());
		if (login) {
			log.trace("zabbix auth sucessfull......  authkey:{}  ", zabbixApi.getAuth());
		} else {
			log.trace("zabbix auth fail......");
		}
	}
}