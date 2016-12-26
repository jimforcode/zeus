package com.zeus.dto;

/**
 * 磁盘信息Dto
 */
public class MemoryInfoDto {

    /**
     * 时间clock
     */
    private Long clock;

    /**
     * 时间
     */
    private String date;

    /**
     * 总使用内存
     */
    private String memoryUsedSpace;

    /**
     * 缓冲内存
     */
    private String memoryBufferSpace;

    /**
     * 总缓存内存
     */
    private String memoryCachedSpace;

    /**
     * 空闲内存
     */
    private String memoryFreeSpace;

    /**
     * 总内存
     */
    private String memoryTotalSpace;

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

    public String getMemoryUsedSpace() {
        return memoryUsedSpace;
    }

    public void setMemoryUsedSpace(String memoryUsedSpace) {
        this.memoryUsedSpace = memoryUsedSpace;
    }

    public String getMemoryBufferSpace() {
        return memoryBufferSpace;
    }

    public void setMemoryBufferSpace(String memoryBufferSpace) {
        this.memoryBufferSpace = memoryBufferSpace;
    }

    public String getMemoryCachedSpace() {
        return memoryCachedSpace;
    }

    public void setMemoryCachedSpace(String memoryCachedSpace) {
        this.memoryCachedSpace = memoryCachedSpace;
    }

    public String getMemoryFreeSpace() {
        return memoryFreeSpace;
    }

    public void setMemoryFreeSpace(String memoryFreeSpace) {
        this.memoryFreeSpace = memoryFreeSpace;
    }

    public String getMemoryTotalSpace() {
        return memoryTotalSpace;
    }

    public void setMemoryTotalSpace(String memoryTotalSpace) {
        this.memoryTotalSpace = memoryTotalSpace;
    }
}
