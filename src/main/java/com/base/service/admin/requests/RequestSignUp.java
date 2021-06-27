package com.base.service.admin.requests;

public class RequestSignUp {
	private String name;
	private String phone;
	private String username;
	private String password;
	private Integer type;

	private RequestStation station;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RequestStation getStation() {
		return station;
	}

	public void setStation(RequestStation station) {
		this.station = station;
	}

	public Integer getType() {
		return type==null?1:type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
