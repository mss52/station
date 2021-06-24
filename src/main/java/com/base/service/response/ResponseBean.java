package com.base.service.response;

public class ResponseBean {
	private long id;
	private String name;

	public ResponseBean() {
		super();
	}

	public ResponseBean(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
