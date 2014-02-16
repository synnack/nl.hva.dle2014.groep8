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
	
	private Long courseId;
	private Long ownerId;
	
	public Lecture(Connection conn) {
		super(conn);
		this.courseId = 0L;
		this.ownerId = 0L;
		this.owner = null;
		this.course = null;

		// Set the object properties
		this.objectName = "lecture";
		this.fieldTypeMap.put("name", FieldType.STRING);
		this.field_SqlNameMap.put("name", "name");
		this.sqlName_FieldMap.put("name", "name");
		
		this.fieldTypeMap.put("ownerId", FieldType.LONG);
		this.field_SqlNameMap.put("ownerId", "owner");
		this.sqlName_FieldMap.put("owner", "ownerId");
		
		this.fieldTypeMap.put("courseId", FieldType.STRING);
		this.field_SqlNameMap.put("courseId", "course");
		this.sqlName_FieldMap.put("course", "courseId");
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
		this.dirty = true;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		this.dirty = true;
	}
}
