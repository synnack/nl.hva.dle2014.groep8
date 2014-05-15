/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.UserCompetency;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

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
    
    public List findByUserId(Long id) {
        try {
            return em.createNamedQuery("UserCompetency.findByUserId", UserCompetency.class)
                     .setParameter("userId", id)
                     .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public UserCompetencyFacade() {
        super(UserCompetency.class);
    }
    
}
