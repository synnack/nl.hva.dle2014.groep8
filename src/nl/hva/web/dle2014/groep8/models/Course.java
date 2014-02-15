package nl.hva.web.dle2014.groep8.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;

public class Course extends DatabaseModel<Course> {
	private String name;
	private User owner;
	private Group managingGroup;

	// These are used for lazy evaluation
	private long ownerId;
	private long managingGroupId;
	
	
	// Constructor
	public Course(Connection conn) {
		super(conn);
		this.owner = null;
		this.managingGroup = null;
		this.ownerId = 0;
		this.managingGroupId = 0;
		
		this.objectName = "course";
	}
	
	@Override
	public PreparedStatement savePreparedStatement() throws SQLException {
		
		// Make sure the other objects are written to the database
		this.owner.save();
		this.managingGroup.save();
		
		PreparedStatement ps;
		
		if (this.id == 0) {
			ps = this.conn.prepareStatement("INSERT INTO " + this.objectName +
					"s (name, owner, managing_group) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
		} else {
			ps = this.conn.prepareStatement("UPDATE " + this.objectName +
					"s set name=?, owner=?, managing_group=? WHERE id=?");
			ps.setLong(4, this.id);
		}
		ps.setString(1, this.name);
		ps.setLong(2, this.owner.getId());
		ps.setLong(3, this.managingGroup.getId());
		
		return ps;
	}
	
	@Override
	public void loadResultSet(ResultSet rs) throws SQLException {
		this.name = rs.getString("name");

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
	
	public User getOwner() throws SQLException {
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
	
	public Group getManagingGroup() throws SQLException {
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
