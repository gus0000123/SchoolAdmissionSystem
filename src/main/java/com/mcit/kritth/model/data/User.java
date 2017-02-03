package com.mcit.kritth.model.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Users")
public class User
{
	@Id
	private String username;
	@NotNull
	private String password;
	@NotNull
	private int authority = 1;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="person_id")
	private Person person;
	
	public User() { }
	
	public String getUsername() { return this.username; }
	public String getPassword() { return this.password; }
	public Person getPerson() { return this.person; }
	public int getAuthority() { return this.authority; }
	
	public void setUsername(String user) { this.username = user; }
	public void setPassword(String password) { this.password = password; }
	public void setPerson(Person p) { this.person = p; }
	public void setAuthority(int auth) { this.authority = auth; }
	
	@Override
	public String toString()
	{
		return person.toString() + " ;" + username + " ;" + password + " ;" + authority;
	}
}
