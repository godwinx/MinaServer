package com.godwin.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Date tool
 * @author xieyj
 * @date:Created on 2014-4-22 上午10:28:33
 */
public class DateUtils {
	/**
	 * 根据指定的格式获取时间字符串
	 * @param format 指定的时间格�?
	 * @return 指定时间格式的字符串
	 */
	public static String getCurrentTime(String format)
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * 返回当前的时间，格式如：2005-12-03 12:03:02
	 * @return String
	 */
	public static String getCurrentTime()
	{
		return getCurrentTime("yyyy-MM-dd HH:mm:ss");
	}
}
