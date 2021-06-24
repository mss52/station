package com.base.lib.db.model.auth;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.base.lib.db.utils.CodeUtils;

@Entity
@Table(name = "`session`")
public class ModelSession
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "device_id")
	private ModelDevice device;
	
	
	@Column(name = "token")
	private String token;

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private ModelUser user;

	@Column(name = "date_creation")
	private Date dateCreation = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_last_access", length = 19)
	private Date dateLastAccess;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_expired", length = 19)
	private Date dateExpired;

	public ModelSession( )
	{
		super();
		generateNewToken();
	}

	public void generateNewToken( )
	{
		token = CodeUtils.generateToken();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ModelDevice getDevice() {
		return device;
	}

	public void setDevice(ModelDevice device) {
		this.device = device;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateLastAccess() {
		return dateLastAccess;
	}

	public void setDateLastAccess(Date dateLastAccess) {
		this.dateLastAccess = dateLastAccess;
	}

	public Date getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(Date dateExpired) {
//		System.out.println("SETTING INVALID: " + dateExpired);
		this.dateExpired = dateExpired;
	}
	
	public boolean isExpired( ) {
		return dateExpired != null;
	}

	public ModelUser getUser() {
		return user;
	}

	public void setUser(ModelUser user) {
		this.user = user;
	}
}
