package com.mcit.kritth.model.data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.mcit.kritth.model.messenger.SystemGroup;

@Entity
@Table(name="Schedules")
public class Schedule
{
	@Id
	@SequenceGenerator(name="sch_id_gen", sequenceName="schedule_id_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sch_id_gen")
	private int id;
	@NotNull
	private Date start_time;
	@NotNull
	private Date end_time;
	@NotNull
	private String headline;
	@NotNull
	private String detail;
	
	@OneToOne
	@JoinColumn(name="creator_id")
	@NotNull
	private Person creator;
	
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinTable(name="as_schedules_system_group",
		joinColumns={@JoinColumn(name="schedule_id")},
		inverseJoinColumns={@JoinColumn(name="system_group_id")})
	private Set<SystemGroup> groupToShow;
	
	public Schedule() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Person getCreator() {
		return creator;
	}

	public void setCreator(Person creator) {
		this.creator = creator;
	}

	public Set<SystemGroup> getGroupToShow() {
		if (groupToShow == null) groupToShow = new HashSet<>();
		return groupToShow;
	}

	public void setGroupToShow(Set<SystemGroup> groupToShow) {
		this.groupToShow = groupToShow;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
