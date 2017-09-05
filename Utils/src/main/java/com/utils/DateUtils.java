package com.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * 计算两个日期相差的天数
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int daysBetween(Date firstDate, Date secondDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstDate);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		long firstTime = calendar.getTimeInMillis();
		calendar.setTime(secondDate);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		long secondTime = calendar.getTimeInMillis();
		long time = firstTime - secondTime;
		int days = (int) (time / 1000 / 60 / 60 / 24);
		return days < 0 ? -days : days;
	}

	/**
	 * 获取某天的天末尾的时间
	 * 
	 * @param addDays
	 * @return
	 */
	public static Date getDayEnd(int addDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.add(Calendar.DAY_OF_MONTH, addDays);
		return calendar.getTime();
	}

	/**
	 * 获取某天的天开始的时间
	 * 
	 * @param addDays
	 * @return
	 */
	public static Date getDayStart(int addDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.add(Calendar.DAY_OF_MONTH, addDays);
		return calendar.getTime();
	}
}
