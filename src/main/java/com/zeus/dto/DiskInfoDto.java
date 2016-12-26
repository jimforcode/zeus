package com.zeus.dto;

/**
 * 磁盘信息Dto
 */
public class DiskInfoDto {

    /**
     * 时间Clock
     */
    private Long clock;

    /**
     * 时间
     */
    private String date;

    /**
     * 剩余空间
     */
    private String availableSpace;

    /**
     * 使用空间
     */
    private String useSpace;


    /**
     * 总空间
     */
    private String totalSpace;


    /**
     * 安装路径
     */
    private String installPath;

    /**
     * 使用空间百分比
     */
    private String usePercent;

    public Long getClock() {
        return clock;
    }

    public void setClock(Long clock) {
        this.clock = clock;
    }

    public String getUsePercent() {
        return usePercent;
    }

    public void setUsePercent(String usePercent) {
        this.usePercent = usePercent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvailableSpace() {
        return availableSpace;
    }

    public void setAvailableSpace(String availableSpace) {
        this.availableSpace = availableSpace;
    }

    public String getUseSpace() {
        return useSpace;
    }

    public void setUseSpace(String useSpace) {
        this.useSpace = useSpace;
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(String totalSpace) {
        this.totalSpace = totalSpace;
    }

    public String getInstallPath() {
        return installPath;
    }

    public void setInstallPath(String installPath) {
        this.installPath = installPath;
    }
}
