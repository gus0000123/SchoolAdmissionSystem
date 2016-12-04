package com.mcit.kritth.model.messenger;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.mcit.kritth.model.data.Person;

@Entity
@Table(name = "Message")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Message
{
	@Id
	@SequenceGenerator(name="msg_id_gen", sequenceName="messages_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="msg_id_gen")
	private int message_id;
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull
	private Person sender;
	@NotNull
	private Date creation_time = new Date();
	@NotNull
	private String headline;
	@NotNull
	private String message;
	@NotNull
	private boolean isTrash = false;
	
	public Message() { }

	public int getId() {
		return message_id;
	}

	public void setId(int id) {
		this.message_id = id;
	}

	public Person getSender() {
		return sender;
	}

	public void setSender(Person sender) {
		this.sender = sender;
	}

	public Date getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Date creation_time) {
		this.creation_time = creation_time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}
}
