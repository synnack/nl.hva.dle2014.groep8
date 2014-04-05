/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author wilco
 */
@Embeddable
public class UserCompetencyPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "competency_id")
    private long competencyId;

    public UserCompetencyPK() {
    }

    public UserCompetencyPK(long userId, long competencyId) {
        this.userId = userId;
        this.competencyId = competencyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(long competencyId) {
        this.competencyId = competencyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) competencyId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCompetencyPK)) {
            return false;
        }
        UserCompetencyPK other = (UserCompetencyPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.competencyId != other.competencyId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserCompetencyPK[ userId=" + userId + ", competencyId=" + competencyId + " ]";
    }
    
}
