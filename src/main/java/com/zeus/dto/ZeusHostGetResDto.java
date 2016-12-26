package com.zeus.dto;

import java.util.List;

/**
 * 获取主机信息返回dto
 */
public class ZeusHostGetResDto {

	private List<ZeusHostDto> zeusHostList;

	public List<ZeusHostDto> getZeusHostList() {
		return zeusHostList;
	}

	public void setZeusHostList(List<ZeusHostDto> zeusHostList) {
		this.zeusHostList = zeusHostList;
	}
}
