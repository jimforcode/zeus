package com.zeus.common.requestEnum;

/**
 * Created by dingaolin on 2016/11/2.
 * 访问方式
 */
public enum NetRequestTypeEnum {
    NET_ALL("ifInOctets_,ifOutOctets_", "ALL"),
    NET_IN_TOTAL_FLOW("ifInOctets_", "总接收流量"), // 总使用内存
    NET_OUT_TOTAL_FLOW("ifOutOctets_", "总发送流量"); // 缓冲内存

    private String code;
    private String desc;

    NetRequestTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
