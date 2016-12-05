package com.zeus.common.listener;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeus.common.config.ApiCfg;

import io.github.hengyunabc.zabbix.api.ZabbixApi;

@Service
public class AutoAuth {

	@Autowired
	private ApiCfg apiCfg;

	@Autowired
	private ZabbixApi zabbixApi;

	private static Logger log = LoggerFactory.getLogger(AutoAuth.class);

	@PostConstruct
	public void init() throws Exception {
		log.info("AutoAuth physical address:{}", this);
		boolean login = zabbixApi.login(apiCfg.getZabbixUser(), apiCfg.getZabbixPassword());
		if (login) {
			log.trace("zabbix auth sucessfull......  authkey:{}  ", zabbixApi.getAuth());
		} else {
			log.trace("zabbix auth fail......");
		}
	}
}