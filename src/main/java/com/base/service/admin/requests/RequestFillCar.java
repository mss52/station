package com.base.service.admin.requests;

public class RequestFillCar {
	
	private RequestCar car;
	
	private String note;
	
	private Double amount;

	
	public RequestCar getCar() {
		return car;
	}

	public void setCar(RequestCar car) {
		this.car = car;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
