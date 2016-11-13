package com.zeus.dto;

/**
 * 网络信息Dto
 */
public class NetInfoDto {

    /**
     * 时间clock
     */
    private Long clock;

    /**
     * 时间
     */
    private String date;

    /**
     * 总接收流量
     */
    private String inTotalFlow;

    /**
     * 总发送流量
     */
    private String outTotalFlow;

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

    public String getInTotalFlow() {
        return inTotalFlow;
    }

    public void setInTotalFlow(String inTotalFlow) {
        this.inTotalFlow = inTotalFlow;
    }

    public String getOutTotalFlow() {
        return outTotalFlow;
    }

    public void setOutTotalFlow(String outTotalFlow) {
        this.outTotalFlow = outTotalFlow;
    }
}