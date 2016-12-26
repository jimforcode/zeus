package com.zeus.dto;

/**
 * 磁盘信息Dto
 */
public class CpuInfoDto {

    /**
     * 时间clock
     */
    private Long clock;

    /**
     * 时间
     */
    private String date;

    /**
     * 用户CPU百分比
     */
    private String userCpuPercent;

    /**
     * 空闲CPU百分比
     */
    private String idleCpuPercent;

    /**
     * 系统CPU百分比
     */
    private String systemCpuPercent;

    /**
     * CPU的当前负载
     */
    private String processorLoadPercent;

    public Long getClock() {
        return clock;
    }

    public void setClock(Long clock) {
        this.clock = clock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserCpuPercent() {
        return userCpuPercent;
    }

    public void setUserCpuPercent(String userCpuPercent) {
        this.userCpuPercent = userCpuPercent;
    }

    public String getIdleCpuPercent() {
        return idleCpuPercent;
    }

    public void setIdleCpuPercent(String idleCpuPercent) {
        this.idleCpuPercent = idleCpuPercent;
    }

    public String getSystemCpuPercent() {
        return systemCpuPercent;
    }

    public void setSystemCpuPercent(String systemCpuPercent) {
        this.systemCpuPercent = systemCpuPercent;
    }

    public String getProcessorLoadPercent() {
        return processorLoadPercent;
    }

    public void setProcessorLoadPercent(String processorLoadPercent) {
        this.processorLoadPercent = processorLoadPercent;
    }
}
