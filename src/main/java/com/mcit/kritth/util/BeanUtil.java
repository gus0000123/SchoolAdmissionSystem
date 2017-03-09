package com.mcit.kritth.util;

import com.mcit.kritth.model.data.Course;

/***
 * This class is created to be a utility for any model bean
 * @author Kritth
 *
 */
public class BeanUtil
{
	/***
	 * Generate course code for the course
	 * @param c Course with filled information
	 * @return expected course code
	 */
	public static String getCourseCode(Course c)
	{
		return c.getDepartment().getDept_code().getDept_code() + c.getClass_level()
				+ ConverterUtil.fillLeft("" + c.getCourse_number(), '0', 2) + "-"
				+ ConverterUtil.fillLeft("" + c.getSection(), '0', 2); 
	}
}
