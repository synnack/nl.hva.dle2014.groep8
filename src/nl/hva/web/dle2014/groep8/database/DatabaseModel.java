package nl.hva.web.dle2014.groep8.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author Wilco Baan Hofman
 *
 * @param <SUBCLASS> Requires a subclass to perform queries on
 */
public abstract class DatabaseModel<SUBCLASS extends DatabaseModel<SUBCLASS>> {
	public enum FieldType {
		INT,
		LONG,
		STRING,
		DATE,
		TIME,
		DATETIME
	};
	
	
	protected Boolean dirty;
	protected Connection conn;
	protected String objectName;
	protected HashMap<String, FieldType> fieldTypeMap;
	protected HashMap<String, String> field_SqlNameMap;
	protected HashMap<String, String> sqlName_FieldMap;
	
	protected Long id;

	/**
	 * Constructs the Database Model abstract class
	 * 
	 * @param connection JDBC connection
	 */
	public DatabaseModel(Connection connection) {
		this.conn = connection;
		this.dirty = true;
		this.fieldTypeMap = new HashMap<String, FieldType>();
		this.field_SqlNameMap = new HashMap<String, String>();
		this.sqlName_FieldMap = new HashMap<String, String>();


		// Add the common id field
		this.fieldTypeMap.put("id", FieldType.LONG);
		this.field_SqlNameMap.put("id", "id");
		this.sqlName_FieldMap.put("id", "id");
	}
	
	/**
	 * This is a generic method to retrieve 1 record from the database based on id
	 * It calls into the subclass to load the ResultSet.
	 * 
	 * @param id The id to get from the database
	 */
	public void getById(Long id) 
			throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
		ArrayList<Filter> filter = new ArrayList<Filter>();
		filter.add(new Filter("id", Filter.Operator.EQUALS, id.toString()));
		select(filter); // FIXME: This does not replace the current object
	}
	
	/**
	 * This is a generic method to constructs a SQL query based on an list of filters
	 * It returns an array of the subclass type when doing queries
	 * 
	 * @param filters ArrayList<Filter> Array of field, operator, value tuples
	 * @return An array of objects matching the filter
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<SUBCLASS> select(ArrayList<Filter> filters) 
			throws SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
		ArrayList<SUBCLASS> dbArr = new ArrayList<SUBCLASS>();
		
		
		// Build a query based on the requested filters
		String query = "";
		Iterator<Filter> iterator = filters.iterator();
		while (iterator.hasNext()) {
			Filter filter = iterator.next();
			query += this.field_SqlNameMap.get(filter.field);
			
			switch(filter.operator) {
				case EQUALS:
					query += " = ?";
					break;
				case GREATER:
					query += " > ?";
					break;
				case GREATER_OR_EQUAL:
					query += " >= ?";
					break;
				case LESS:
					query += " < ?";
					break;
				case LESS_OR_EQUAL:
					query += " <= ?";
					break;
				case LIKE:
					query += " LIKE ?";
			}
			
			query += " AND ";
		}
		query += "1=1"; // Correct, I am too lazy :-)
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + 
				this.objectName + "s WHERE " + query);
		
		// Write out the rest of the query.
		iterator = filters.iterator();
		for (int i = 1; iterator.hasNext(); i++) {
			Filter filter = iterator.next();
			ps.setString(i, filter.value);
		}
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData meta = rs.getMetaData();
		int column_cnt = meta.getColumnCount();
		while(rs.next()) {
			DatabaseModel<SUBCLASS> field = getClass().newInstance();
			for (int i = 0; i < column_cnt; i++) {
				String columnName = sqlName_FieldMap.get(meta.getColumnName(i));
				switch(this.fieldTypeMap.get(columnName)) {
					case INT:
						getClass().getField(columnName)
								.set(field, rs.getInt(i));
						break;
					case LONG:
						getClass().getField(columnName)
								.set(field, rs.getLong(i));
						break;
					case STRING:
						getClass().getField(columnName)
								.set(field, rs.getString(i));
						break;
					case DATE:
					case DATETIME:
						getClass().getField(columnName)
								.set(field, rs.getDate(i));
						break;
					case TIME:
						getClass().getField(columnName)
								.set(field, rs.getTime(i));
						break;
				}
			}
		}
		
		return dbArr;
	}
	
	/**
	 * Save the object in the database
	 * 
	 * @throws SQLException
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public void save() throws SQLException, IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
		if (!this.dirty) {
			return;
		}

		PreparedStatement ps;
		if (this.id != 0L) {
			String query = "";
			int fieldCount = 0;
			
			Iterator<Map.Entry<String,FieldType>> it = fieldTypeMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String,FieldType> pair = (Map.Entry<String,FieldType>)it.next();
				String sqlField = this.field_SqlNameMap.get(pair.getKey());
				
				if (query != "") {
					query += ",";
				}
				query += sqlField + "=?";
				fieldCount++;
			}
			ps = conn.prepareStatement("UPDATE " + this.objectName +
					"s SET " + query + " WHERE id=?");
			ps.setLong(fieldCount, this.id);
		
		} else {
			String part1 = "";
			String part2 = "";
			Iterator<Map.Entry<String,FieldType>> it = fieldTypeMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String,FieldType> pair = (Map.Entry<String,FieldType>)it.next();
				String sqlField = this.field_SqlNameMap.get(pair.getKey());
				
				if (part1 != "") {
					part1 += ",";
					part2 += ",";
				}
				part1 += sqlField;
				part2 += "?";
			}
			ps = conn.prepareStatement("INSERT INTO " + this.objectName +
					"s (" + part1 + ") VALUES (" + part2 + ")");
		}
		
		Iterator<Map.Entry<String,FieldType>> it = fieldTypeMap.entrySet().iterator();
		for (int i = 1;it.hasNext(); i++) {
			Map.Entry<String,FieldType> pair = (Map.Entry<String,FieldType>)it.next();
			// This ugly beast is ugly!
			// FIXME: This only handles strings now!
			ps.setString(i, getClass().getField(pair.getKey()).get(getClass().getField(pair.getKey())).toString());
		}
	
		
		if (this.id == 0L) {
			int rows_updated = ps.executeUpdate();
			if (rows_updated > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					this.id = rs.getLong(1);
				}
			}
		}
		
		this.dirty = false;
	}
}
