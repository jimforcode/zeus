package com.zeus.service;

public interface ZabbixService {
	boolean auth(String username, String pwd);

	Object hostGet(String[] host);
}
