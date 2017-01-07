package com.mcit.kritth.bo.template;

import com.mcit.kritth.model.data.Course;
import com.mcit.kritth.model.data.CourseWork;

public interface CourseWorkBO extends BaseBO<CourseWork> {
	boolean updateCourse(CourseWork obj, Course objToAdd);
}
