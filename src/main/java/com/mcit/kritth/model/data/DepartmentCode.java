package com.mcit.kritth.model.data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="department_code",
		uniqueConstraints = { @UniqueConstraint(columnNames={ "dept_code" }) })
public class DepartmentCode
{
	@Id
	private String dept_code;
	@NotNull	
	private String dept_name;
	
	public DepartmentCode() { }

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
}
