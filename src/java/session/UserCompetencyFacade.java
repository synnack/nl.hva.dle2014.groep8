/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.UserCompetency;
import entity.UserCompetencyPK;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author wilco
 */
@Stateless
public class UserCompetencyFacade extends AbstractFacade<UserCompetency> {
    @PersistenceContext(unitName = "Digital_Learning_EnvironmentPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public boolean addUserCompetency(long userId, long competencyId, short skillLevel) {
        UserCompetency userCompetency = new UserCompetency();

        userCompetency.setUserCompetencyPK(
                 new UserCompetencyPK(
                         userId, 
                         competencyId));
        userCompetency.setSkillLevel(skillLevel);
        userCompetency.setLastModified(new Date());
        
        try {
            em.persist(userCompetency);
            em.flush();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation c: violations) {
                System.err.println("Message: " + c.getMessage());
                System.err.println("Message: " + c.getLeafBean());
            }
            return false;
        } catch (PersistenceException e) {
            System.err.println("Message: " + e.getMessage());
            return false;
        }
        return true;
    }
        
    public UserCompetency findByUserIdCompetencyId(long userId, long competencyId) {
        try {
            return em.createNamedQuery("UserCompetency.findByUserIdCompetencyId", UserCompetency.class)
                     .setParameter("userId", userId)
                     .setParameter("competencyId", competencyId)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    public boolean editUserCompetency(long userId, long competencyId, short skillLevel) {
        UserCompetency userCompetency = findByUserIdCompetencyId(userId, competencyId);
        userCompetency.setSkillLevel(skillLevel);
        userCompetency.setLastModified(new Date());
        
        try {
            em.persist(userCompetency);
            em.flush();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation c: violations) {
                System.err.println("Message: " + c.getMessage());
                System.err.println("Message: " + c.getLeafBean());
            }
            return false;
        } catch (PersistenceException e) {
            System.err.println("Message: " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean removeUserCompetency(long userId, long competencyId) {
        UserCompetency userCompetency = new UserCompetency();
        userCompetency.setUserCompetencyPK(
                 new UserCompetencyPK(
                         userId, 
                         competencyId));
        
        try {
            em.remove(userCompetency);
            em.flush();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation c: violations) {
                System.err.println("Message: " + c.getMessage());
                System.err.println("Message: " + c.getLeafBean());
            }
            return false;
        } catch (PersistenceException e) {
            System.err.println("Message: " + e.getMessage());
            return false;
        }
        return true;
    }
    
    public UserCompetencyFacade() {
        super(UserCompetency.class);
    }
    
}
