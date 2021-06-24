package com.base.base;

public class Return<T> {
	private T data;
	private String code;
	private boolean success;
	
	
	public Return() {
		super();
	}
	public Return(T data, String code, boolean success) {
		super();
		this.data = data;
		this.code = code;
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
