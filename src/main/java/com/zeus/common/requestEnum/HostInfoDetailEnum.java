package com.zeus.common.requestEnum;

/**
 * Created by dingaolin on 2016/11/2.
 * 访问方式
 */
public enum HostInfoDetailEnum {
    HOST_ALL("sysName", "ALL"),
    HOST_SYS_NAME("sysName", "服务器名称"); //服务器名称

    private String code;
    private String desc;

    HostInfoDetailEnum(String code, String desc) {
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
