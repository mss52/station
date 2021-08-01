package com.base.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static Date clearDate(Date d) {
		if(d==null) {
			return null;
		}
		Calendar c=Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date midnigth(Date d) {
		if(d==null) {
			return null;
		}
		Calendar c=Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

}
