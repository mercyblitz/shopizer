package com.salesmanager.core.util;

import java.util.Date;

public class CloneUtils {
	
	private CloneUtils() {};
	
	public static Date clone(Date date) {
		if (date != null) {
			return (Date) date.clone();
		}
		return null;
	}

}
