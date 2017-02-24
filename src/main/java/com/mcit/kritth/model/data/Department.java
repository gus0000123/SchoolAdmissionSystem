package com.mcit.kritth.model.data;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Departments")
public class Department
{
	@Id
	@SequenceGenerator(name="dpt_id_gen", sequenceName="department_id_seq")
	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator="dpt_id_gen")
	private int dept_id;
	@NotNull
	private int budget;
	@Temporal(TemporalType.TIMESTAMP)
	private Date founding_date = new Date();
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="dept_code")
	@NotNull
	private DepartmentCode dept_code;
	
	@OneToOne
	@JoinColumn(name="dean_id")
	private Employee dean;
	
	public Department() { }
	
	public int getDeptId() {
		return dept_id;
	}
	public void setDeptId(int deptId) {
		this.dept_id = deptId;
	}
	public DepartmentCode getCode() {
		return dept_code;
	}
	public void setCode(DepartmentCode code) {
		this.dept_code = code;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public Date getFounding_date() {
		return founding_date;
	}
	public void setFounding_date(Date founding_date) {
		this.founding_date = founding_date;
	}
	public Employee getDean() {
		return dean;
	}
	public void setDean(Employee dean) {
		this.dean = dean;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Department)
			return this.hashCode() == o.hashCode();
		else
			return false;
	}
	
	@Override
	public int hashCode()
	{
		return dept_code.getDept_code().hashCode();
	}
}
