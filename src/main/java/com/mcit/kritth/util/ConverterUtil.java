package com.mcit.kritth.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * This class is utility class to help converting and formatting data
 * @author Kritth
 *
 */
public class ConverterUtil
{
	/***
	 * Convert string into date
	 * @param in String where it can be parsed into date
	 * @return Date object
	 */
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
	
	/***
	 * Fill the left side with 0
	 * @param toFill
	 * @param filler
	 * @param length
	 * @return Filled string
	 */
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
