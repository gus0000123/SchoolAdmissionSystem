package com.mcit.kritth.model.library;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="author")
public class Author
{
	@Id
	@SequenceGenerator(name="at_id_gen", sequenceName="author_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="at_id_gen")
	private int id;
	@NotNull
	private String first_name;
	private String middle_name;
	@NotNull
	private String last_name;
	@NotNull
	private String bibliography;
	private String note;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="author")
	private Set<Resource> resources;
	
	public Author() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getBibliography() {
		return bibliography;
	}

	public void setBibliography(String bibliography) {
		this.bibliography = bibliography;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
}
