package com.zeus.service;

import com.zeus.dto.zabbix.AuthResult;

public interface ZabbixService {
	AuthResult auth(String username,String pwd);
}
