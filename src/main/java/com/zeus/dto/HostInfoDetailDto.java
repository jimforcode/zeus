package com.zeus.dto;

/**
 * 主机信息Dto
 */
public class HostInfoDetailDto {

    /**
     * 资源名称
     */
    private String hostName;

    /**
     * 显示名称
     */
    private String hostNickName;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 当前可用性
     */
    private String isAvailable;

    /**
     * 运行时长
     */
    private String operateTime;

    /**
     * 所属域
     */
    private String ownedDomain;

    /**
     * 当前策略
     */
    private String monitorStrategy;

    /**
     *  健康状况
     */
    private String healthStar;

    /**
     *  监控时长
     */
    private String monitoTotalTime;

    /**
     *  上次登陆
     */
    private String lastSeenTime;

    public String getHealthStar() {
        return healthStar;
    }

    public void setHealthStar(String healthStar) {
        this.healthStar = healthStar;
    }

    public String getMonitoTotalTime() {
        return monitoTotalTime;
    }

    public void setMonitoTotalTime(String monitoTotalTime) {
        this.monitoTotalTime = monitoTotalTime;
    }

    public String getLastSeenTime() {
        return lastSeenTime;
    }

    public void setLastSeenTime(String lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostNickName() {
        return hostNickName;
    }

    public void setHostNickName(String hostNickName) {
        this.hostNickName = hostNickName;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOwnedDomain() {
        return ownedDomain;
    }

    public void setOwnedDomain(String ownedDomain) {
        this.ownedDomain = ownedDomain;
    }

    public String getMonitorStrategy() {
        return monitorStrategy;
    }

    public void setMonitorStrategy(String monitorStrategy) {
        this.monitorStrategy = monitorStrategy;
    }
}
