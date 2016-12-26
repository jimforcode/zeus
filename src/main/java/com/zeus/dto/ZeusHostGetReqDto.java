package com.zeus.dto;

/**
 * 获取主机信息请求dto
 */
public class ZeusHostGetReqDto {

	/**
	 * 机构编号
	 */
	private String agencyNo;
	
	/**
	 * 主机类型
	 */
	private String hostType;
	
	/**
	 * 主机IP
	 */
	private String hsotIp;

	public String getAgencyNo() {
		return agencyNo;
	}

	public void setAgencyNo(String agencyNo) {
		this.agencyNo = agencyNo;
	}

	public String getHostType() {
		return hostType;
	}

	public void setHostType(String hostType) {
		this.hostType = hostType;
	}

	public String getHsotIp() {
		return hsotIp;
	}

	public void setHsotIp(String hsotIp) {
		this.hsotIp = hsotIp;
	}
	
}
