package com.base.service.response;

public class ResponseLogin 
{
	private Long verificationId;
	private boolean loggedIn;
	private ResponseSession session;
	
	public ResponseLogin() {
		super();
	}
	public ResponseLogin(ResponseSession session) {
		super();
		this.loggedIn = true;
		this.session = session;
	}
	public ResponseLogin(Long verificationId) {
		super();
		this.verificationId = verificationId;
		this.loggedIn = false;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public ResponseSession getSession() {
		return session;
	}
	public void setSession(ResponseSession session) {
		this.session = session;
	}
	public Long getVerificationId() {
		return verificationId;
	}
	public void setVerificationId(Long verificationId) {
		this.verificationId = verificationId;
	}
}
