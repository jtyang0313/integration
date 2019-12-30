package com.yjt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils
{
	public static final String dateFormatStr = "yyyyMMddHHmmss";
	
	public static Date getToday(String format)
	{
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		try
		{
			return dateformat.parse(dateformat.format(new Date()));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static String format(String format)
	{
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		return dateformat.format(new Date());
	}

	public static String format(Date date, String format)
	{
		if(date==null)
		{
			return "";
		}
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		return dateformat.format(date);
	}
	
	public static void main(String[] args)
	{
		
	}
	
	public static Date getDate(String dateStr, String format)
	{
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		try
		{
			return dateformat.parse(dateStr);
		}
		catch (ParseException e)
		{
			// TODO Auto- generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
