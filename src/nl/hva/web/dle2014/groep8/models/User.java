package nl.hva.web.dle2014.groep8.models;

import java.math.BigInteger;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User extends DatabaseModel<User> {
	private String username;
	private String passwordHash;
	private String givenName;
	private String surName;
	private String displayName;
	private String email;

	/*
	 * This constructor takes a database connection and passes it to the Database constructor
	 */
	public User(Connection conn) {
		super(conn);
		this.objectName = "user";
	}
	
	
	/*
	 * @see nl.hva.web.dle2014.groep8.database.DatabaseModel#savePreparedStatement()
	 * 
	 * This method prepares insert and update queries for this class object. It gets 
	 * called by the save() method in the abstract superclass. 
	 */
	@Override
	public PreparedStatement savePreparedStatement() throws SQLException {
		PreparedStatement ps;
		if (this.id == 0) {
			ps = this.conn.prepareStatement("INSERT INTO " + this.objectName +
					"s (username, password_hash, given_name, surname, display_name, email) "+
					"VALUES (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
		} else {
			ps = this.conn.prepareStatement("UPDATE " + this.objectName +
					"s set username=?, password_hash=?, given_name=?, surname=?, " +
					"display_name=?, email=? WHERE id=?");
			ps.setLong(7, this.id);
		}
		
		ps.setString(1, this.username);
		ps.setString(2, this.passwordHash);
		ps.setString(3, this.givenName);
		ps.setString(4, this.surName);
		ps.setString(5, this.displayName);
		ps.setString(6, this.email);
		
		return ps;
	}
	/*
	 * @see nl.hva.web.dle2014.groep8.database.DatabaseModel#loadResultSet(java.sql.ResultSet)
	 * 
	 * This method loads the object values from a select query, it gets called by the 
	 * select() and getById() functions in the abstract superclass. 
	 */
	@Override
	public void loadResultSet(ResultSet rs) throws SQLException {
		this.username = rs.getString("username");
		this.passwordHash = rs.getString("password_hash");
		this.givenName = rs.getString("given_name");
		this.surName = rs.getString("surname");
		this.displayName = rs.getString("display_name");
		this.email = rs.getString("email");
	}
	
	
	/*
	 * This method checks if the argument supplied matches the known password 
	 */
	public Boolean isPasswordCorrect(String password) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
        byte[] salt = this.passwordHash.substring(0, 
        		this.passwordHash.indexOf(":")).getBytes();
        
        return this.passwordHash != generateHash(password, salt);      
	}
	
	/*
	 * This method sets a new password 
	 */
	public void setPassword(String password) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
        byte[] salt = getRandomSalt();
        
        this.passwordHash = generateHash(password, salt);
	}
	
	/*
	 * This method is private and generates a PBKDF2 hash of the password
	 */
	private static String generateHash(String password, byte[] salt) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		int iterations = 1000;
        char[] chars = password.toCharArray();
        
		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}
	
	/*
	 * This method generates a 16-byte random salt, to be used for hash generation
	 */
	private static byte[] getRandomSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	/*
	 * This method converts a byte array to a hex string
	 */
	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		
		int paddingLength = (array.length * 2) - hex.length();
		
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}
	
	/*
	 * Get/Setters below here
	 * 
	 * Not that there are no passwordHash get/setters, because we only want to write 
	 * and compare.
	 * 
	 * There is also no setter for userId, that is derived from the database.
	 * 
	 */
	
	public long getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
		this.dirty = true;
	}
	
	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
		this.dirty = true;
	}

	public String getSurName() {
		return this.surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
		this.dirty = true;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
		this.dirty = true;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.dirty = true;
	}
	
}
