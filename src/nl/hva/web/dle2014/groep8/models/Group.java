package nl.hva.web.dle2014.groep8.models;

import java.sql.Connection;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;
import nl.hva.web.dle2014.groep8.database.DatabaseModel.FieldType;

public class Group extends DatabaseModel<Group> {
	private String name;
	private User manager;
	
	private Long managerId;
	
	public Group(Connection conn) {
		super(conn);
		this.managerId = 0L;
		this.manager = null;
		
		// Set the object properties
		this.objectName = "group";
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
	
	public User getManager() {
		return this.manager;
	}
	
	public void setManager(User manager) {
		this.manager = manager;
		this.dirty = true;
	}
}
