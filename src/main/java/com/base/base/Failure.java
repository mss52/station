package com.base.base;

public class Failure<T> extends Return<T> {

	public Failure(String error) {
		super(null, error, false);
	}
	public Failure() {
		super(null, null, false);
	}
}
