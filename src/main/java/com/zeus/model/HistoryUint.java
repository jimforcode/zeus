package com.zeus.model;

/**
 * history_uint表
 */
public class HistoryUint {

	private Long itemid;

	private Long clock;

	private Long value;

	private Long ns;

	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public Long getClock() {
		return clock;
	}

	public void setClock(Long clock) {
		this.clock = clock;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getNs() {
		return ns;
	}

	public void setNs(Long ns) {
		this.ns = ns;
	}
}
