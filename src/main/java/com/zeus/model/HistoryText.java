package com.zeus.model;

/**
 * history_text表
 */
public class HistoryText {

	private Long id;

	private Long itemid;

	private Long clock;

	private String value;

	private Long ns;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getNs() {
		return ns;
	}

	public void setNs(Long ns) {
		this.ns = ns;
	}
}
