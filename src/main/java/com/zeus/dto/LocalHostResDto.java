package com.zeus.dto;

import java.util.List;

/**
 * 局域网主机列表
 */
public class LocalHostResDto {
	
	
	private List<LocalHostDto> localHostList;

	public List<LocalHostDto> getLocalHostList() {
		return localHostList;
	}

	public void setLocalHostList(List<LocalHostDto> localHostList) {
		this.localHostList = localHostList;
	}
	
}
