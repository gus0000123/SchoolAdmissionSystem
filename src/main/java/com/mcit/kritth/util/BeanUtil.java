package com.mcit.kritth.util;

import com.mcit.kritth.model.data.Course;

public class BeanUtil
{
	public static String getCourseCode(Course c)
	{
		return c.getDepartment().getCode().getDept_code() + c.getClass_level()
				+ ConverterUtil.fillLeft("" + c.getCourse_number(), '0', 2) + "-"
				+ ConverterUtil.fillLeft("" + c.getSection(), '0', 2); 
	}
}
