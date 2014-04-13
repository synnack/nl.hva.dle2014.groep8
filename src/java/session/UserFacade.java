/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wilco
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "Digital_Learning_EnvironmentPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public User findByUsername(String username) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                     .setParameter("username", username)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public Boolean addUser(String username, 
                           String password,
                           String givenName,
                           String surname,
                           String displayName,
                           String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGivenName(givenName);
        user.setSurname(surname);
        user.setDisplayName(displayName);
        user.setEmail(email);

        em.persist(user);
       return true;
    }
    
    public UserFacade() {
        super(User.class);
    }
    
}
