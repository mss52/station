package com.base.base;

public class Success<T> extends Return<T> {

	public Success(T data) {
		super(data, null, true);
	}

	public Success() {
		super(null, null, true);
	}
	
}
