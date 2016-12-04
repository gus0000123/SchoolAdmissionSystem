package com.mcit.kritth.model.messenger;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.mcit.kritth.model.data.Person;

@Entity
@Table(name="System_Group"
		, uniqueConstraints=@UniqueConstraint(columnNames="name"))
public class SystemGroup
{
	@Id
	@SequenceGenerator(name="sysg_id_gen", sequenceName="system_group_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sysg_id_gen")
	private int sys_group_id;
	@NotNull
	private String name;
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(name="as_person_system_group",
		joinColumns={@JoinColumn(name="sys_group_id")},
		inverseJoinColumns={@JoinColumn(name="ID")})
	private Set<Person> recipients;
	
	public SystemGroup() { }

	public int getId() {
		return sys_group_id;
	}

	public void setId(int id) {
		this.sys_group_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Person> getRecipients() {
		if (recipients == null) recipients = new HashSet<>();
		return recipients;
	}

	public void setRecipients(Set<Person> recipients) {
		this.recipients = recipients;
	}
}
