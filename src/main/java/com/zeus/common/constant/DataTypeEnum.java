package com.zeus.common.constant;

/**
 * Created by dingaolin on 2016/11/2.
 * 访问方式
 */
public enum DataTypeEnum {
    SNMP("snmp"),
    AGENT("agent");

    DataTypeEnum(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

}
