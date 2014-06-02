/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Course;
import entity.DLEGroup;
import entity.Lecture;
import entity.User;
import entity.UserCompetency;
import java.util.Collection;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import session.CourseFacade;

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

    private boolean store(Object object) {
        return store(object, false);
    }
    
    private boolean store(Object object, boolean newObj) {
        try {
            if (newObj) {
                em.persist(object);
            } else {
                em.merge(object);
            }
            em.flush();
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            for (ConstraintViolation c: violations) {
                System.out.println("Message: " + c.getMessage());
                System.out.println("Message: " + c.getLeafBean());
            }
            return false;
        } catch (PersistenceException e) {
            System.err.println("Message: " + e.getMessage());

            return false;
        }
        return true;
    }   
    
    public User findByUsername(String username) {
        try {
            return em.createNamedQuery("User.findByUsername", User.class)
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

        return store(user, true);
    }

    public boolean modifyUser(Long id,
            String givenName,
            String surname,
            String email,
            String password) {

        User user = find(id);
        user.setGivenName(givenName);
        user.setSurname(surname);
        user.setEmail(email);
        if (password != null) {
            user.setPassword(password);
        }

        return store(user);
    }
    
    public Collection<Lecture> findUpcomingLectures(Long id) {
        try {
            return em.createNamedQuery("User.findUpcomingLectures", Lecture.class)
                     .setParameter("id", id)
                     .getResultList();
        } catch (NoResultException e) {
            return null;
        }        
    }
    
    public boolean modifyUser(String username,
                           String givenName,
                           String surname,
                           String email) {
        User user = new User();
        user.setGivenName(givenName);
        user.setSurname(surname);
        user.setEmail(email);
        
        return store(user);
    }
    
    public boolean addUserCourse(User user, Course course) {
        Collection<Course> courseCollection = user.getCourseCollection();
        courseCollection.add(course);
        user.setCourseCollection(courseCollection);   
        return store(user);
    }
    
    public boolean removeUserCourse(User user, Course course) {
        Collection<Course> courseCollection = user.getCourseCollection();
        courseCollection.remove(course);
        user.setCourseCollection(courseCollection);
        return store(user);
    }
    
    public boolean addUserGroup(User user, DLEGroup group) {
        Collection<DLEGroup> groupCollection = user.getDLEGroupCollection();
        groupCollection.add(group);
        user.setDLEGroupCollection(groupCollection);
        return store(user);
    }
    
    public boolean removeUserGroup(User user, DLEGroup group) {
        Collection<DLEGroup> groupCollection = user.getDLEGroupCollection();
        groupCollection.remove(group);
        user.setDLEGroupCollection(groupCollection);
        return store(user);
    }
    
    public UserFacade() {
        super(User.class);
    }
    
}
