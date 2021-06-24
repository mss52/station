package com.base.service.response;

import com.base.lib.db.model.auth.ModelSession;

public class ResponseSession {

	private long id;

	private Long device;

	private String token;

	private ResponseUser user;

	
	public ResponseSession() {
		super();
	}

	public ResponseSession(long id, Long device, String token, ResponseUser user) {
		super();
		this.id = id;
		this.device = device;
		this.token = token;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getDevice() {
		return device;
	}

	public void setDevice(Long device) {
		this.device = device;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ResponseUser getUser() {
		return user;
	}

	public void setUser(ResponseUser user) {
		this.user = user;
	}

	public static ResponseSession fromModel(ModelSession session) {
		return new ResponseSession(session.getId(), session.getDevice() == null ? null : session.getDevice().getId(),
				session.getToken(), ResponseUser.fromModel(session.getUser()));
	}

}
