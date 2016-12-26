package com.zeus.dto;

/**
 * 主机信息Dto
 */
public class ZeusHostDto {
	
	/**
     * 主机ID
     */
    private String hostId;
	
	/**
     * 机构编号
     */
    private String agencyNo;

    /**
     * 机构名称
     */
    private String agencyName;
    
    /**
     * 主机类型
     */
    private String hostType;
    
    /**
     * 主机IP
     */
    private String hsotIp;

    /**
     * 主机名称
     */
    private String hostName;
    
    /**
     * 创建用户
     */
    private String createdUser;

    /**
     * 修改用户
     */
    private String modifiedUser;

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getAgencyNo() {
		return agencyNo;
	}

	public void setAgencyNo(String agencyNo) {
		this.agencyNo = agencyNo;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
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

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	
}
