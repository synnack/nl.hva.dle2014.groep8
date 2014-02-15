package nl.hva.web.dle2014.groep8.models;

import java.sql.Connection;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;

public class Group extends DatabaseModel<Group> {
	private String name;
	private User manager;
	
	public Group(Connection conn) {
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
	
	public User getManager() {
		return this.manager;
	}
	
	public void setManager(User manager) {
		this.manager = manager;
		this.dirty = true;
	}
}
