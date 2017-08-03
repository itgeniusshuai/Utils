package com.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * ��������������������
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int daysBetween(Date firstDate, Date secondDate) {
		long firstTime = firstDate.getTime();
		long secondTime = secondDate.getTime();
		long time = firstTime - secondTime;
		int days = (int) (time/1000/60/60/24);
		return days;
	}
	/**
	 * ��ȡĳ�����ĩβ��ʱ��
	 * @param addDays
	 * @return
	 */
	public static Date getDayEnd(int addDays){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.add(Calendar.DAY_OF_MONTH, addDays);
		return calendar.getTime();
	}
	/**
	 * ��ȡĳ����쿪ʼ��ʱ��
	 * @param addDays
	 * @return
	 */
	public static Date getDayStart(int addDays){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.add(Calendar.DAY_OF_MONTH, addDays);
		return calendar.getTime();
	}
}