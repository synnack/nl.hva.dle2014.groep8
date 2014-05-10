/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

//import entity.Competency;
import entity.User;
import java.util.Set;
//import entity.UserCompetency;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

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
    
    public boolean addUser(String username, 
                           String password,
                           String givenName,
                           String surname,
                           String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setGivenName(givenName);
        user.setSurname(surname);
        user.setEmail(email);

        try {
            em.persist(user);
            em.flush();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation c: violations) {
                System.out.println("Message: " + c.getMessage());
                System.out.println("Message: " + c.getLeafBean());
            }
            return false;
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }
    
    public boolean modifyUser(String username,
                           String givenName,
                           String surname,
                           String email) {
        User user = new User();
        user.setGivenName(givenName);
        user.setSurname(surname);
        user.setEmail(email);

        try {
            em.persist(user);
            em.flush();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation c: violations) {
                System.out.println("Message: " + c.getMessage());
                System.out.println("Message: " + c.getLeafBean());
            }
            return false;
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }
    
    //public Boolean addCompetency(User user, Competency competency, Int skillLevel) {
        //UserCompetency competency = new UserCompetency(UserCompetencyPK, skillLevel, null);
        
    //}
    
    public UserFacade() {
        super(User.class);
    }
    
}
