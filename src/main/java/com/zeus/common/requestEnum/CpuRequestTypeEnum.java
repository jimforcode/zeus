package com.zeus.common.requestEnum;

/**
 * Created by dingaolin on 2016/11/2.
 * 访问方式
 */
public enum CpuRequestTypeEnum {
    CPU_ALL("ssCpuUser,ssCpuIdle,ssCpuSystem,hrProcessorLoad", "ALL"), //ALL
    CPU_USER_PERCENT("ssCpuUser", "用户CPU百分比"), // 用户CPU百分比
    CPU_IDLE_PERCENT("ssCpuIdle", "空闲CPU百分比"), // 空闲CPU百分比
    CPU_SYSTEM_PERCENT("ssCpuSystem", "系统CPU百分比"), // 系统CPU百分比
    CPU_PROCESSOR_LOAD_PERCENT("hrProcessorLoad", "CPU的当前负载"); // CPU的当前负载

    private String code;
    private String desc;

    CpuRequestTypeEnum(String code, String desc) {
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
