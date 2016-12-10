package com.mcit.kritth.model.messenger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Announcements")
public class Announcement extends Message
{
	@ManyToOne
	@NotNull
	private SystemGroup system_group;

	public Announcement() { }
	
	public SystemGroup getSystem_group() {
		return system_group;
	}

	public void setSystem_group(SystemGroup system_group) {
		this.system_group = system_group;
	}
}