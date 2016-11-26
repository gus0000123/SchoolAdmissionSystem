package beans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Users"
	, uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
public class User
{
	@Id
	@GeneratedValue								private int user_id;
	@Column(name="username")					private String username;
	@NotNull									private String password;
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="person_id", unique=true)	private Person person;
	@NotNull									private int authority = 1;
	
	public User() { }
	
	public String getUser() { return this.username; }
	public String getPassword() { return this.password; }
	public Person getPerson() { return this.person; }
	public int getAuthority() { return this.authority; }
	
	public void setUser(String user) { this.username = user; }
	public void setPassword(String password) { this.password = password; }
	public void setPerson(Person p) { this.person = p; }
	public void setAuthority(int auth) { this.authority = auth; }
	
	@Override
	public String toString()
	{
		return person.toString() + " ;" + username + " ;" + password + " ;" + authority;
	}
}
