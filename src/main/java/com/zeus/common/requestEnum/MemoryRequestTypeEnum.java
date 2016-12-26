package com.zeus.common.requestEnum;

/**
 * Created by dingaolin on 2016/11/2.
 * 访问方式
 */
public enum MemoryRequestTypeEnum {
    MEMORY_ALL("memAvailReal,memBuffer,memCached,memTotalReal,memTotalFree", "ALL"),
    MEMORY_USED_SPACE("memAvailReal", "总使用内存"), // 总使用内存
    MEMORY_BUFFER_SPACE("memBuffer", "总内存缓冲"), // 缓冲内存
    MEMORY_CACHED_SPACE("memCached", "总缓存内存"), // 总缓存内存
    MEMORY_FREE_SPACE("memTotalFree", "空闲内存"), // 空闲内存
    MEMORY_TOTAL_SPACE("memTotalReal", "总内存"); // 总内存

    private String code;
    private String desc;

    MemoryRequestTypeEnum(String code, String desc) {
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
