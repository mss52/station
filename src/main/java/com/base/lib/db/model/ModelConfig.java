package com.base.lib.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "config")
public class ModelConfig {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "`key`")
	private String key;
	
	@Column(name = "`value`")
	private String value;

	@Column(name = "created_at")
	private Date createdAt = new Date();
	
	@Column(name = "deactivate_at")
	private Date deactivateAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDeactivateAt() {
		return deactivateAt;
	}

	public void setDeactivateAt(Date deactivateAt) {
		this.deactivateAt = deactivateAt;
	}
}
