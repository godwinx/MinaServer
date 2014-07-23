package com.godwin.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;


/**
 * 日志记录工具�?记录日志到控制台或日志文�?
 */
public class LogUtils
{
	
	private static Log logger;
	
	public static void initialLog(){
		logger = LogFactory.getLog(LogUtils.class);
		PropertyConfigurator.configure("log4j.properties");
	}
	
	/**
	 * 打印异常日志
	 * @param logger - 日志记录�?
	 * @param e - 异常
	 */
	public static void error(Throwable e)
	{
		StringBuilder sb=new StringBuilder();
		sb.append("\r\n");
		sb.append("localizedmessage:"+e.getLocalizedMessage());
		sb.append("\r\n");
		sb.append("message:"+e.getMessage());
		sb.append("\r\n");
		sb.append("cause:"+e.getCause());
		sb.append("\r\n");
		sb.append("exceptionclass:");
		sb.append(e.getClass());
		sb.append("\r\n");
		sb.append("stacktrack:");
		for(StackTraceElement tmp: e.getStackTrace())
		{
			sb.append(tmp);
			sb.append("\r\n");
		}
		logger.error(sb);
	}
	
	public static Log getLoger() {
		return logger;
	}
	
}
