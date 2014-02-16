package nl.hva.web.dle2014.groep8.models;

import java.sql.Connection;
import java.util.Date;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;
import nl.hva.web.dle2014.groep8.database.DatabaseModel.FieldType;

public class Document extends DatabaseModel<Document> {
	private String name;
	private Course course;
	private User owner;
	private Date lastModified;
	private byte[] content;
	
	private Long courseId;
	private Long ownerId;
	
	public Document(Connection conn) {
		super(conn);
		this.courseId = 0L;
		this.ownerId = 0L;
		this.owner = null;
		this.course = null;

		// Set the object properties
		this.objectName = "document";
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
}
