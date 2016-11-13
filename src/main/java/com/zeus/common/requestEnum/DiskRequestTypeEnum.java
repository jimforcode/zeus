package com.zeus.common.requestEnum;

/**
 * Created by dingaolin on 2016/11/2.
 * 访问方式
 */
public enum DiskRequestTypeEnum {
    DISK_ALL("dskAvail,dskUsed,dskTotal,dskPercent", "ALL"),
    DISK_AVAILABLE_SPACE("dskAvail", "磁盘可用空间"), // 磁盘剩余空间
    DISK_USED_SPACE("dskUsed", "磁盘使用空间"), // 磁盘使用空间
    DISK_TOTAL_SPACE("dskTotal", "磁盘总空间"), // 磁盘总空间
    DISK_USE_PERCENT("dskPercent", "磁盘使用空间百分比"); // 磁盘使用空间百分比

    private String code;
    private String desc;

    DiskRequestTypeEnum(String code, String desc) {
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
