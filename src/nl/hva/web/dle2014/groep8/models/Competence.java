package nl.hva.web.dle2014.groep8.models;

import java.sql.Connection;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;

public class Competence extends DatabaseModel<Competence> {
	private String name;
	
	
	public Competence(Connection conn) {
		super(conn);
		
		// Set the object properties
		this.objectName = "competence";
		this.fieldTypeMap.put("name", FieldType.STRING);
		this.field_SqlNameMap.put("name", "name");
		this.sqlName_FieldMap.put("name", "name");
		
	}
	
	/*
	 * Get/Setters below here
	 */
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
		this.dirty = true;
	}
}
