package nl.hva.web.dle2014.groep8.models;

import java.sql.Connection;
import java.util.Date;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;

public class Lecture extends DatabaseModel<Lecture> {
	private String name;
	private Course course;
	private Date startDate;
	private Date endDate;
	private User owner;
	private Date lastModified;
	
	public Lecture(Connection conn) {
		super(conn);
		this.objectName = "lecture";
	}
	
	/*
	 * Get/Setters below here
	 */
	
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
		this.dirty = true;
	}
	
	public User getOwner() {
		return this.owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
		this.dirty = true;
	}

	public Course getCourse() {
		return this.course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
		this.dirty = true;
	}
	
	public Date getLastModified() {
		return this.lastModified;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		this.dirty = true;
	}
}
