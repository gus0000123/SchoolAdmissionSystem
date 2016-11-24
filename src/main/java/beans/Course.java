package beans;

import java.util.List;
import java.util.Map;

import enums.DayOfWeek;
import enums.ScheduleTime;

public class Course
{
	private String course_id;
	private String course_name;
	private String course_description;
	private int capacity;
	private Map<DayOfWeek, ScheduleTime> schedule;
	private List<Course> prerequisite;
}
