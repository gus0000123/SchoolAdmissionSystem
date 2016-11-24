package beans;

import java.util.Date;
import java.util.List;

import enums.DepartmentCode;

public class Department
{
	private int dept_id;
	private String name;
	private DepartmentCode code;
	private int budget;
	private Date founding_date;
	private List<Student> students;
	private List<Employee> employees;
	private Employee dean;
}
