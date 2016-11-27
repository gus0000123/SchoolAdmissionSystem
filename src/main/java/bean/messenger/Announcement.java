package bean.messenger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Announcements")
public class Announcement extends Message
{
	@ManyToOne(cascade={CascadeType.ALL})
	@NotNull					private SystemGroup system_group;

	public Announcement() { }
	
	public SystemGroup getSystem_group() {
		return system_group;
	}

	public void setSystem_group(SystemGroup system_group) {
		this.system_group = system_group;
	}
}