package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities
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
}
