package com.base.service.admin.requests;

public class RequestStation {
	private Long id;
	private String name;
	private String phone;
	private RequestLocation location;
	private Boolean closed;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RequestLocation getLocation() {
		return location;
	}

	public void setLocation(RequestLocation location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

}
