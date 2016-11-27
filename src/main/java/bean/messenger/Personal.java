package bean.messenger;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import bean.data.Person;

@Entity
@Table(name="Personal_Messages")
public class Personal extends Message
{
	@OneToMany(cascade={CascadeType.ALL})
	@NotNull			private Set<Person> recipients;
	
	public Personal() { }

	public Set<Person> getRecipients() {
		if (recipients == null) recipients = new HashSet<>();
		return recipients;
	}

	public void setRecipients(Set<Person> recipients) {
		this.recipients = recipients;
	}
}
