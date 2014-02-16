package nl.hva.web.dle2014.groep8.models;

import java.math.BigInteger;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import nl.hva.web.dle2014.groep8.database.DatabaseModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;


public class User extends DatabaseModel<User> {
	private String username;
	private String passwordHash;
	private String givenName;
	private String surName;
	private String displayName;
	private String email;

	/**
	 * This constructor takes a database connection and passes it to the Database constructor
	 * @param conn The JDBC connection
	 */
	public User(Connection conn) {
		super(conn);
		this.objectName = "user";
	}
	
	
	
	
	/**
	 * This method checks if the argument supplied matches the known password 
	 *
	 * @param password The password to verify
	 * @return Password correct yes/no
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public Boolean isPasswordCorrect(String password) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
        byte[] salt = this.passwordHash.substring(0, 
        		this.passwordHash.indexOf(":")).getBytes();
        
        return this.passwordHash != generateHash(password, salt);      
	}
	
	/**
	 * This method sets a new password for the user. 
	 *
	 * @param password Password to set
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public void setPassword(String password) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
        byte[] salt = getRandomSalt();
        
        this.passwordHash = generateHash(password, salt);
	}
	
	/**
	 * This method is private and generates a PBKDF2 hash of the password
	 *
	 * @param password The password to hash
	 * @param salt Salt to use for hashing
	 * @return password hash
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
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
	
	/**
	 * This method generates a 16-byte random salt, to be used for hash generation
	 * 
	 * @return a 16-byte random salt
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] getRandomSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}

	/**
	 * This method converts a byte array to a hex string
	 * 
	 * @param array Byte array to convert to hex
	 * @return hexadecimal string representation of the supplied array
	 * @throws NoSuchAlgorithmException
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
