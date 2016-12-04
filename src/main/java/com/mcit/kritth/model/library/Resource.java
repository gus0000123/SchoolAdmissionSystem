package com.mcit.kritth.model.library;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="resource")
public class Resource
{
	@Id
	@SequenceGenerator(name="res_id_gen", sequenceName="resources_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="res_id_gen")
	private int id;
	
	@NotNull
	private String title;
	private String subtitle;
	@NotNull
	private String ISBN;
	@NotNull
	private String description;
	@NotNull
	private int pages;
	@NotNull
	private boolean availability = false;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date published_date;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date date_added;
	
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Author author;
	
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private ResourceType type;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="as_resource_contributor",
			joinColumns={@JoinColumn(name="resource_id")},
			inverseJoinColumns={@JoinColumn(name="resource_contributor_id")})
	private Set<Author> contributor;
	
	public Resource() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Set<Author> getContributor() {
		return contributor;
	}

	public void setContributor(Set<Author> contributor) {
		this.contributor = contributor;
	}

	public Date getDate_added() {
		return date_added;
	}

	public void setDate_added(Date date_added) {
		this.date_added = date_added;
	}
}