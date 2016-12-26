package com.zeus.common.requestEnum;

/**
 * Created by dingaolin on 2016/11/2.
 * 访问方式
 */
public enum IORequestTypeEnum {
    IO_ALL("ssIOSent,ssIOReceive", "ALL"),
    IO_SENT_RATE("ssIOSent", "总使用内存"), // 发送速率
    IO_RECEIVE_RATE("ssIOReceive", "总内存缓冲"); // 接收速率

    private String code;
    private String desc;

    IORequestTypeEnum(String code, String desc) {
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
