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
@Table(name = "competency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competency.findAll", query = "SELECT c FROM Competency c ORDER BY c.name"),
    @NamedQuery(name = "Competency.findById", query = "SELECT c FROM Competency c WHERE c.id = :id"),
    @NamedQuery(name = "Competency.findByName", query = "SELECT c FROM Competency c WHERE c.name = :name")})
public class Competency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "competencyCollection")
    private Collection<Course> courseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competency")
    private Collection<UserCompetency> userCompetencyCollection;

    public Competency() {
    }

    public Competency(Long id) {
        this.id = id;
    }

    public Competency(Long id, String name) {
        this.id = id;
        this.name = name;
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
    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
    }

    @XmlTransient
    public Collection<UserCompetency> getUserCompetencyCollection() {
        return userCompetencyCollection;
    }

    public void setUserCompetencyCollection(Collection<UserCompetency> userCompetencyCollection) {
        this.userCompetencyCollection = userCompetencyCollection;
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
        if (!(object instanceof Competency)) {
            return false;
        }
        Competency other = (Competency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Competency[ id=" + id + " ]";
    }
    
}
