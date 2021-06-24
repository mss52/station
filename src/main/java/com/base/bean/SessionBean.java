package com.base.bean;

import com.base.lib.db.model.auth.ModelDevice;
import com.base.lib.db.model.auth.ModelSession;
import com.base.lib.db.model.auth.ModelUser;

public class SessionBean {
	private long id;

	private ModelUser user;

	private ModelDevice device;
	
	
	public SessionBean() {
		super();
	}

	public ModelDevice getDevice() {
		return device;
	}

	public void setDevice(ModelDevice device) {
		this.device = device;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ModelUser getUser() {
		return user;
	}

	public void setUser(ModelUser user) {
		this.user = user;
	}
	public void setSession(ModelSession session) {
		user=session.getUser();
		id=session.getId();
	}
}
