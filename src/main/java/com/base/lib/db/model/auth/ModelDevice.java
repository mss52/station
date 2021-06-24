package com.base.lib.db.model.auth;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device")
public class ModelDevice
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "operating_system")
	private Integer operatingSystem;

	@Column(name = "operating_name")
	private String operatingName;

	@Column(name = "is_active")
	private boolean active = true;

	@Column(name = "app_version", length = 16)
	private String appVersion;

	@Column(name = "brand", length = 32)
	private String brand;

	@Column(name = "model", length = 64)
	private String model;

	@Column(name = "uid")
	private String uid;

	@Column(name = "os_version", length = 16)
	private String osVersion;
	
	@Column(name = "fcm_token")
	private String fcmToken;

	@Column(name = "date_creation", length = 19)
	private Date dateCreation;

	@Column(name = "date_deactivation", length = 19)
	private Date dateDeactivation;
	
	public ModelDevice() {
		super();
	}
	
	public ModelDevice(long id) {
		super();
		this.id = id;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateDeactivation() {
		return dateDeactivation;
	}

	public void setDateDeactivation(Date dateDeactivation) {
		this.dateDeactivation = dateDeactivation;
	}
}
