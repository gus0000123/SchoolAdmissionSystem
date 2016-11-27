package bean.data;

import java.util.HashSet;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Departments")
public class Department
{
	@Id
	@GeneratedValue
	private int dept_id;
	@NotNull
	private int budget;
	@Temporal(TemporalType.TIMESTAMP)
	private Date founding_date = new Date();
	
	@OneToOne
	@JoinColumn(name="dept_code")
	@NotNull
	private DepartmentCode dept_code;
	
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinTable(name="as_departments_students",
		joinColumns={@JoinColumn(name="dept_id")},
		inverseJoinColumns={@JoinColumn(name="student_id")})
	private Set<Student> students;
	
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinTable(name="as_departments_employees",
		joinColumns={@JoinColumn(name="dept_id")},
		inverseJoinColumns={@JoinColumn(name="employee_id")})
	private Set<Employee> employees;
	
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
	public Set<Student> getStudents() {
		if (students == null) students = new HashSet<Student>();
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public Employee getDean() {
		return dean;
	}
	public void setDean(Employee dean) {
		this.dean = dean;
	}
	public Set<Employee> getEmployees() {
		if (employees == null) employees = new HashSet<Employee>();
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}
