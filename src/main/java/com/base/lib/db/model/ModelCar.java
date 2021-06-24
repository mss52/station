package com.base.lib.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "car")
public class ModelCar {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "added_by_user")
	private Long addedByUser;
	
	@Column(name = "car_owner_name")
	private String carOwnerName;
	
	@Column(name = "plate_number")
	private String plateNumber;

	@Column(name = "plate_code")
	private String plateCode;
	
	@Column(name = "note")
	private String note;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "last_filled_id")
	private ModelCarFillingHistory lastFilled;
	
	@Column(name = "allowed_after_day_count")
	private Long allowedAfterDayCount;
	
	@Column(name = "created_at")
	private Date createdAt = new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public ModelCarFillingHistory getLastFilled() {
		return lastFilled;
	}

	public void setLastFilled(ModelCarFillingHistory lastFilled) {
		this.lastFilled = lastFilled;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getAllowedAfterDayCount() {
		return allowedAfterDayCount;
	}

	public void setAllowedAfterDayCount(Long allowedAfterDayCount) {
		this.allowedAfterDayCount = allowedAfterDayCount;
	}

	public Long getAddedByUser() {
		return addedByUser;
	}

	public void setAddedByUser(Long addedByUser) {
		this.addedByUser = addedByUser;
	}
}
