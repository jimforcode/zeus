package com.zeus.dto;

/**
 * 磁盘信息Dto
 */
public class IOInfoDto {

    /**
     * 时间clock
     */
    private Long clock;

    /**
     * 时间
     */
    private String date;

    /**
     * 接收速率
     */
    private String receiveRate;

    /**
     * 发送速率
     */
    private String sendRate;

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

    public String getReceiveRate() {
        return receiveRate;
    }

    public void setReceiveRate(String receiveRate) {
        this.receiveRate = receiveRate;
    }

    public String getSendRate() {
        return sendRate;
    }

    public void setSendRate(String sendRate) {
        this.sendRate = sendRate;
    }
}
