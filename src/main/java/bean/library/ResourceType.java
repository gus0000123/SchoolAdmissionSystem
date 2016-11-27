package bean.library;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="resource_type"
		, uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
public class ResourceType
{
	@Id @GeneratedValue
	private int id;
	@NotNull
	private String name;
	@NotNull
	private String description;
	
	public ResourceType() { }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

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
