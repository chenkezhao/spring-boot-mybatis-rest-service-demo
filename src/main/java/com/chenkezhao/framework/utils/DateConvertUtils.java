package com.chenkezhao.framework.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateConvertUtils {
	public static Date toDateTime(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sm.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date toDateTime2(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sm.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date toDate(String dateStr) {
		if (dateStr == null) {
			return null;
		}
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sm.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dateToString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		return sm.format(date);
	}

	public static String dateTimeToString(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sm.format(date);
	}

	public static String dateTimeToFileName(boolean month) {
		SimpleDateFormat sm;
		if (month) {
			sm = new SimpleDateFormat("yyyy/MM/dd");
		} else {
			sm = new SimpleDateFormat("yyyyMMddHHmmss");
		}

		Date date = new Date();
		return sm.format(date);
	}

	/**
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static int compareDate(String date1, String date2, int stype) {
		int n = 0;

		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		date2 = date2 == null ? getCurrentDate() : date2;

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}

		n = n - 1;

		if (stype == 2) {
			n = (int) n / 365;
		}
		return n;
	}

	public static List<String> getCurrentYearMonthList(String year) {
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		List<String> monthList = new ArrayList<String>();
		try {
			c1.setTime(df.parse(year + "-01"));
			c2.setTime(df.parse(year + "-12"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (!c1.after(c2)) {
			monthList.add(df.format(c1.getTime()));
			c1.add(Calendar.MONTH, 1);
		}
		return monthList;
	}

	/**
	 * 得到当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(date);
	}

	/**
	 * 得到当前年
	 * 
	 * @return
	 */
	public static String getCurrentYear() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");
		return simple.format(date);
	}

	/**
	 * 得到当前年月
	 * 
	 * @return
	 */
	public static String getCurrentYearMonth() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM");
		return simple.format(date);
	}

	/**
	 * 得到当前日期时间
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simple.format(date);
	}

	public static void main(String[] args) throws Exception{
		//DateConvertUtils.getCurrentYearMonthList("2014");
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatteryear = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatterMonth = new SimpleDateFormat("MM");
		Calendar c = Calendar.getInstance();
		Date date = ymd.parse("2015-10-02");
		c.setTime(date);
		String accountDay = "2";
		// 起始时间
		Calendar peroidMonthStartDate = DateConvertUtils
				.getPeroidMonthStartDate(c,accountDay);
		System.out.println("startdate:"+ymd.format(peroidMonthStartDate.getTime()));
		// 结止时间
		Calendar peroidMonthEndDate = DateConvertUtils
				.getPeroidMonthEndDate(c,accountDay);
		System.out.println("enddate"+ymd.format(peroidMonthEndDate.getTime()));
	}

	public static Calendar getPeroidMonthEndDate(Calendar c,String perioddate) {
		try {
			//Calendar c = Calendar.getInstance();
			SimpleDateFormat ym = new SimpleDateFormat("yyyy-MM");
			String endYearMonth = ym.format(c.getTime()) + "-" + perioddate;
			SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = ymd.parse(endYearMonth);
			c.setTime(date);
			c.add(Calendar.MONTH, 1);
			if(perioddate.equals("01")){
				c.add(Calendar.DAY_OF_MONTH, -1);
			}
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Calendar getPeroidMonthStartDate(Calendar endYearMonth,String day) {
		try {
			//Calendar c = Calendar.getInstance();
			if(day.equals("01")){
				endYearMonth.set(Calendar.DAY_OF_MONTH, 1); 
				//c.add(Calendar.MONTH, -1);
				//c.add(Calendar.DATE, -1);
			}else{
				//c.setTime(endYearMonth.getTime());
				//endYearMonth.add(Calendar.MONTH, -1);
				endYearMonth.add(Calendar.DAY_OF_MONTH, 1);
			}
			//Calendar calendar = Calendar.getInstance();
			
			return endYearMonth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Calendar getPeroidYearStartDate(Calendar endYearMonth) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(endYearMonth.getTime());
			c.add(Calendar.MONTH, -12);
			c.add(Calendar.DAY_OF_MONTH, 1);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到本月的第一天
	 * 
	 * @return
	 */
	public static Date getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 得到本月的最后一天
	 * 
	 * @return
	 */
	public static Date getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
}
