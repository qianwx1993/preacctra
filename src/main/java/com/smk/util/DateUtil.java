package com.smk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Version 1.0
 * @Since JDK1.8
 * @Author HYK
 * @Date 2019/1/29 0029 16:38
 */
public class DateUtil {

	public static final String YEAR_MONTH_DAY = "yyyyMMdd";
	public static final String HOUR_MINUTE_SECOND = "HHmmss";

	public static String getDate(String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date());
	}

	public static String getYearMonthDay() {
		return getDate(YEAR_MONTH_DAY);
	}

	public static String getHourMinuteSecond() {
		return getDate(HOUR_MINUTE_SECOND);
	}

}
