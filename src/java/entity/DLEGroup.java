/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wilco
 */
@Entity
@Table(name = "`group`")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DLEGroup.findAll", query = "SELECT d FROM DLEGroup d"),
    @NamedQuery(name = "DLEGroup.findById", query = "SELECT d FROM DLEGroup d WHERE d.id = :id"),
    @NamedQuery(name = "DLEGroup.findByName", query = "SELECT d FROM DLEGroup d WHERE d.name = :name")})
public class DLEGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "dLEGroupCollection")
    private Collection<User> userCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "managingGroup")
    private Collection<Course> courseCollection;
    @JoinColumn(name = "manager", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User manager;

    public DLEGroup() {
    }

    public DLEGroup(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
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
        if (!(object instanceof DLEGroup)) {
            return false;
        }
        DLEGroup other = (DLEGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DLEGroup[ id=" + id + " ]";
    }
    
}
