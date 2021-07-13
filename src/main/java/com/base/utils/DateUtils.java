package com.base.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static Date clearDate(Date d) {
		Calendar c=Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}


}
