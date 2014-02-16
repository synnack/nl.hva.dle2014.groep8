package nl.hva.web.dle2014.groep8.models;

import java.sql.Connection;
import java.sql.SQLException;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;

public class Course extends DatabaseModel<Course> {
	private String name;
	private User owner;
	private Group managingGroup;

	// These are used for lazy evaluation
	private Long ownerId;
	private Long managingGroupId;
	
	
	/**
	 * Constructor for Course objects
	 * 
	 * @param conn JDBC database connection
	 */
	public Course(Connection conn) {
		super(conn);
		
		// Initialize the fields
		this.owner = null;
		this.managingGroup = null;
		this.ownerId = 0L;
		this.managingGroupId = 0L;
		
		// Set the object properties
		this.objectName = "course";
		this.fieldTypeMap.put("name", FieldType.STRING);
		this.field_SqlNameMap.put("name", "name");
		this.sqlName_FieldMap.put("name", "name");
		
		this.fieldTypeMap.put("ownerId", FieldType.LONG);
		this.field_SqlNameMap.put("ownerId", "owner");
		this.sqlName_FieldMap.put("owner", "ownerId");
		
		this.fieldTypeMap.put("managingGroupId", FieldType.STRING);
		this.field_SqlNameMap.put("managingGroupId", "managing_group");
		this.sqlName_FieldMap.put("managing_group", "managingGroupId");
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
	
	public User getOwner() throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
		if (this.owner == null) {
			if (this.managingGroupId == 0) {
				throw new SQLException("There is no owner.");
			} else {
				this.owner = new User(this.conn);
				this.owner.getById(this.ownerId);
			}
		}
		return this.owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
		this.dirty = true;
	}
	
	public Group getManagingGroup() throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
		if (this.managingGroup == null) {
			if (this.managingGroupId == 0) {
				throw new NullPointerException("There is no managing group.");
			} else {
				this.managingGroup = new Group(this.conn);
				this.managingGroup.getById(this.managingGroupId);				
			}
		}
			
		return this.managingGroup;
	}
	
	public void setManagingGroup(Group managingGroup) {
		this.managingGroup = managingGroup;
		this.dirty = true;
	}
}
