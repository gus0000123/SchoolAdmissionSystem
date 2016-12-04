package com.mcit.kritth.model.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="student_admission_status",
		uniqueConstraints = { @UniqueConstraint(columnNames = { "status" } ) })
public class StudentAdmissionStatus
{
	@Id @GeneratedValue 
	private int id;
	@NotNull	
	private String status;
	@NotNull
	private String description;
	
	public StudentAdmissionStatus() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
