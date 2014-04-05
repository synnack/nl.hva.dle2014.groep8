/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wilco
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPasswordHash", query = "SELECT u FROM User u WHERE u.passwordHash = :passwordHash"),
    @NamedQuery(name = "User.findByGivenName", query = "SELECT u FROM User u WHERE u.givenName = :givenName"),
    @NamedQuery(name = "User.findBySurname", query = "SELECT u FROM User u WHERE u.surname = :surname"),
    @NamedQuery(name = "User.findByDisplayName", query = "SELECT u FROM User u WHERE u.displayName = :displayName"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password_hash")
    private String passwordHash;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "given_name")
    private String givenName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "display_name")
    private String displayName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @JoinTable(name = "user_in_group", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "group_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<DLEGroup> dLEGroupCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    private Collection<Course> courseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    private Collection<Document> documentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    private Collection<Lecture> lectureCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<UserCompetency> userCompetencyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    private Collection<DLEGroup> dLEGroupCollection1;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String username, String password, String givenName, String surname, String displayName, String email) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.id = id;
        this.username = username;
        this.passwordHash = generateHash(password, getRandomSalt());
        this.givenName = givenName;
        this.surname = surname;
        this.displayName = displayName;
        this.email = email;
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

            return this.passwordHash.equals(generateHash(password, salt));      
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
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<DLEGroup> getDLEGroupCollection() {
        return dLEGroupCollection;
    }

    public void setDLEGroupCollection(Collection<DLEGroup> dLEGroupCollection) {
        this.dLEGroupCollection = dLEGroupCollection;
    }

    @XmlTransient
    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Collection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    @XmlTransient
    public Collection<Lecture> getLectureCollection() {
        return lectureCollection;
    }

    public void setLectureCollection(Collection<Lecture> lectureCollection) {
        this.lectureCollection = lectureCollection;
    }

    @XmlTransient
    public Collection<UserCompetency> getUserCompetencyCollection() {
        return userCompetencyCollection;
    }

    public void setUserCompetencyCollection(Collection<UserCompetency> userCompetencyCollection) {
        this.userCompetencyCollection = userCompetencyCollection;
    }

    @XmlTransient
    public Collection<DLEGroup> getDLEGroupCollection1() {
        return dLEGroupCollection1;
    }

    public void setDLEGroupCollection1(Collection<DLEGroup> dLEGroupCollection1) {
        this.dLEGroupCollection1 = dLEGroupCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ id=" + id + " ]";
    }
    
}
