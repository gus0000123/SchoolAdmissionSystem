package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Departments")
public class Department
{
	@Id
	@GeneratedValue							private int dept_id;
	@OneToOne
	@JoinColumn(name="dept_code")
	@NotNull								private DepartmentCode dept_code;
	@NotNull								private int budget;
	@Temporal(TemporalType.TIMESTAMP)		private Date founding_date = new Date();
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Departments_Students",
		joinColumns={@JoinColumn(name="dept_id")},
		inverseJoinColumns={@JoinColumn(name="student_id")})
											private List<Student> students;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="Departments_Employees",
		joinColumns={@JoinColumn(name="dept_id")},
		inverseJoinColumns={@JoinColumn(name="employee_id")})
											private List<Employee> employees;
	@OneToOne
	@JoinColumn(name="dean_id")				private Employee dean;
	
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
	public List<Student> getStudents() {
		if (students == null) students = new ArrayList<Student>();
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public Employee getDean() {
		return dean;
	}
	public void setDean(Employee dean) {
		this.dean = dean;
	}
	public List<Employee> getEmployees() {
		if (employees == null) employees = new ArrayList<Employee>();
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
