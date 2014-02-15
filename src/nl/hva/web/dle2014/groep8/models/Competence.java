package nl.hva.web.dle2014.groep8.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;

public class Competence extends DatabaseModel<Competence> {
	private String name;
	
	
	public Competence(Connection conn) {
		super(conn);
		this.objectName = "competence";
	}
	
	@Override
	public PreparedStatement savePreparedStatement() throws SQLException {
		PreparedStatement ps;
		if (this.id == 0) {
			ps = this.conn.prepareStatement("INSERT INTO " + this.objectName +
					"s (name) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
		} else {
			ps = this.conn.prepareStatement("UPDATE " + this.objectName +
					"s set name=? WHERE id=?");
			ps.setLong(2, this.id);
		}
		
		ps.setString(1, this.name);
		
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
}
