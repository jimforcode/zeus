package com.zeus.dto;

/**
 * 局域网主机Dto
 */
public class LocalHostDto {
	
    /**
     * ip
     */
    private String ip;

    /**
     * 主机名称
     */
    private String hostName;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
}
