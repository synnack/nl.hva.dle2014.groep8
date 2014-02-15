package nl.hva.web.dle2014.groep8.models;

import java.sql.Connection;
import java.util.Date;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;

public class Document extends DatabaseModel<Document> {
	private String name;
	private Course course;
	private User owner;
	private Date lastModified;
	
	public Document(Connection conn) {
		super(conn);
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
}
