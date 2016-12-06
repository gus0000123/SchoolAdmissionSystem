package com.mcit.kritth.model.messenger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.mcit.kritth.model.data.Person;

@Entity
@Table(name="Personal_Messages")
public class Personal extends Message
{
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="receiver_id")
	@NotNull
	private Person receiver;
	@NotNull
	private boolean important = false;
	@NotNull
	private boolean isTrash = false;
	
	public Personal() { }

	public Person getReceiver() {
		return receiver;
	}

	public void setReceiver(Person receiver) {
		this.receiver = receiver;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}
}
