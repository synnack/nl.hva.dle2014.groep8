/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wilco
 */
@Entity
@Table(name = "user_has_competency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserCompetency.findAll", query = "SELECT u FROM UserCompetency u"),
    @NamedQuery(name = "UserCompetency.findByUserId", query = "SELECT u FROM UserCompetency u WHERE u.userCompetencyPK.userId = :userId"),
    @NamedQuery(name = "UserCompetency.findByCompetencyId", query = "SELECT u FROM UserCompetency u WHERE u.userCompetencyPK.competencyId = :competencyId"),
    @NamedQuery(name = "UserCompetency.findBySkillLevel", query = "SELECT u FROM UserCompetency u WHERE u.skillLevel = :skillLevel"),
    @NamedQuery(name = "UserCompetency.findByUserIdCompetencyId", query = "SELECT u FROM UserCompetency u WHERE u.userCompetencyPK.userId = :userId AND u.userCompetencyPK.competencyId = :competencyId"),
    @NamedQuery(name = "UserCompetency.findByLastModified", query = "SELECT u FROM UserCompetency u WHERE u.lastModified = :lastModified")})
public class UserCompetency implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserCompetencyPK userCompetencyPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "skill_level")
    private short skillLevel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @JoinColumn(name = "competency_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Competency competency;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserCompetency() {
    }

    public UserCompetency(UserCompetencyPK userCompetencyPK) {
        this.userCompetencyPK = userCompetencyPK;
    }

    public UserCompetency(UserCompetencyPK userCompetencyPK, short skillLevel, Date lastModified) {
        this.userCompetencyPK = userCompetencyPK;
        this.skillLevel = skillLevel;
        this.lastModified = lastModified;
    }

    public UserCompetency(long userId, long competencyId) {
        this.userCompetencyPK = new UserCompetencyPK(userId, competencyId);
    }

    public UserCompetencyPK getUserCompetencyPK() {
        return userCompetencyPK;
    }

    public void setUserCompetencyPK(UserCompetencyPK userCompetencyPK) {
        this.userCompetencyPK = userCompetencyPK;
    }

    public short getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(short skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Competency getCompetency() {
        return competency;
    }

    public void setCompetency(Competency competency) {
        this.competency = competency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userCompetencyPK != null ? userCompetencyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCompetency)) {
            return false;
        }
        UserCompetency other = (UserCompetency) object;
        if ((this.userCompetencyPK == null && other.userCompetencyPK != null) || (this.userCompetencyPK != null && !this.userCompetencyPK.equals(other.userCompetencyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserCompetency[ userCompetencyPK=" + userCompetencyPK + " ]";
    }
    
}
