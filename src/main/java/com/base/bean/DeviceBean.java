package com.base.bean;

import com.base.lib.db.model.auth.ModelDevice;

public class DeviceBean {
	private long id;

	private Integer operatingSystem;

	private String operatingName;

	private boolean active = true;

	private String appVersion;

	private String fcmToken;

	public DeviceBean(long id, Integer operatingSystem, String operatingName, boolean active, String appVersion,
			String fcmToken) {
		super();
		this.id = id;
		this.operatingSystem = operatingSystem;
		this.operatingName = operatingName;
		this.active = active;
		this.appVersion = appVersion;
		this.fcmToken = fcmToken;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(Integer operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getOperatingName() {
		return operatingName;
	}

	public void setOperatingName(String operatingName) {
		this.operatingName = operatingName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public static DeviceBean fromModel(ModelDevice device) {
		if (device == null) {
			return null;
		}
		return new DeviceBean(device.getId(), device.getOperatingSystem(), device.getOperatingName(), device.isActive(),
				device.getAppVersion(), device.getFcmToken());
	}
}
