package com.base.base;

public class ResponseBase<T> {
	private T data;
	private Dialog dialog;
	private String code;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Dialog getDialog() {
		return dialog;
	}
	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
