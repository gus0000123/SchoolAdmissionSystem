package bean.messenger;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import bean.data.Person;

@Entity
@Table(name = "Message")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Message
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)		private int id;
	@ManyToOne
	@NotNull											private Person sender;
	@NotNull											private Date creation_time = new Date();
	@NotNull											private String message;
	
	public Message() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
}
