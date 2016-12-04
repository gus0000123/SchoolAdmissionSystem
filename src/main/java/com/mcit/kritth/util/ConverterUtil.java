package com.mcit.kritth.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterUtil
{
	public static Date convertStringToDate(String in)
	{
		try
		{
			return new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(in);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return new Date();
		}
	}
	
	public static String fillLeft(String toFill, char filler, int length)
	{
		String result = toFill;
		
		while (result.length() < length)
		{
			result = filler + result;
		}
		
		return result;
	}
}
