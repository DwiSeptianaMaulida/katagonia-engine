package com.mulyadime.backend.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {

	private static final String FORMAT_DISPLAY = "dd/MM/yyyy";
	
	private static final String FORMAT_DISPLAY_TIMESTAMP = "DD/MM/YYYY HH24:MM:SS";

	public static Date getCurrentDate() {
		Date dt = new Date(System.currentTimeMillis());
		log.info("Current Date: {}", format(dt, FORMAT_DISPLAY_TIMESTAMP));
		return dt;
	}

	private static String format(Date value, String format) {
		if (value != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String strDate = sdf.format(value);
			log.debug("Before: {}; After: {};", value, strDate);
			return strDate;
		}
		
		return StringUtil.EMPTY_STRING;
	}

	public static String formatToDisplay(Date value) {
		if (value != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DISPLAY);
			String strDate = sdf.format(value);
			return strDate;
		}
		
		return StringUtil.EMPTY_STRING;
	}

	public static Date toSQLDate(Date value) {
		return new Date(value.getTime());
	}

}
