package com.base.service.admin.requests;

public class RequestCar {
	
	private Long id;
	
	private String carOwnerName;
	
	private String plateNumber;

	private String plateCode;
	
	private Long allowedAfterDayCount;
	
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarOwnerName() {
		return carOwnerName;
	}

	public void setCarOwnerName(String carOwnerName) {
		this.carOwnerName = carOwnerName;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getPlateCode() {
		return plateCode;
	}

	public void setPlateCode(String plateCode) {
		this.plateCode = plateCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getAllowedAfterDayCount() {
		return allowedAfterDayCount;
	}

	public void setAllowedAfterDayCount(Long allowedAfterDayCount) {
		this.allowedAfterDayCount = allowedAfterDayCount;
	}
}
