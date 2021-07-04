package com.base.lib.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.base.lib.db.model.auth.ModelUser;


@Entity
@Table(name = "car_filling_history")
public class ModelCarFillingHistory {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "car_id")
	private long carId;

	@ManyToOne
	@JoinColumn(name = "filling_user_id")
	private ModelUser fillingUser;

	@Column(name = "note")
	private String note;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "`date`")
	private Date date = new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	
	public ModelUser getFillingUser() {
		return fillingUser;
	}

	public void setFillingUser(ModelUser fillingUser) {
		this.fillingUser = fillingUser;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
