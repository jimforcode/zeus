package com.zeus.dto;

import java.util.List;

/**
 * 保存主机信息请求Dto
 */
public class ZeusHostSaveReqDto {
	
	private List<ZeusHostDto> zeusHostList;

	public List<ZeusHostDto> getZeusHostList() {
		return zeusHostList;
	}

	public void setZeusHostList(List<ZeusHostDto> zeusHostList) {
		this.zeusHostList = zeusHostList;
	}
	
}
