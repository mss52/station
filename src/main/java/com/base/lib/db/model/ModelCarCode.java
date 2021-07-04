package com.base.lib.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car_code")
public class ModelCarCode {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "`code`")
	private String code;
	
	
	@Column(name = "`allowed_after_day_count`")
	private long allowedAfterDayCount=1;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public long getAllowedAfterDayCount() {
		return allowedAfterDayCount;
	}


	public void setAllowedAfterDayCount(long allowedAfterDayCount) {
		this.allowedAfterDayCount = allowedAfterDayCount;
	}
	
	
}
