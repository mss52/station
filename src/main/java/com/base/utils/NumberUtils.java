package com.base.utils;

public class NumberUtils {
	
	public static Long parseLong(String text) {
		try {
			return Long.parseLong(text);
		}catch(Exception ex){}
		return null;
	}
	public static Integer pasrseInteger(String text) {
		try {
			return Integer.parseInt(text);
		}catch(Exception ex){}
		return null;
	}

}
