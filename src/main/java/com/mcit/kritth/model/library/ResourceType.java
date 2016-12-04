package com.mcit.kritth.model.library;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="resource_type"
		, uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class ResourceType
{
	@Id
	private String name;
	@NotNull
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
