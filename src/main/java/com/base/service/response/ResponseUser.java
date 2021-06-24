package com.base.service.response;

import com.base.lib.db.model.auth.ModelUser;

public class ResponseUser {
	private long id;
	
	private ResponseBean type;

	private ResponseBean station;

	private String name;

	public ResponseUser() {
		super();
	}

	public ResponseUser(long id, ResponseBean station, String name,ResponseBean type) {
		super();
		this.id = id;
		this.station = station;
		this.name = name;
		this.type=type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public ResponseBean getStation() {
		return station;
	}

	public void setStation(ResponseBean station) {
		this.station = station;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public ResponseBean getType() {
		return type;
	}

	public void setType(ResponseBean type) {
		this.type = type;
	}

	public static ResponseUser fromModel(ModelUser user) {
		return new ResponseUser(user.getId(), user.getStation() == null ? null
				: new ResponseBean(user.getStation().getId(), user.getStation().getName()), user.getName(),
				user.getUserType()==1?new ResponseBean(user.getUserType(), "STATION"):
						new ResponseBean(user.getUserType(), "USER"));
	}

}
