/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Competency;
import entity.Course;
import java.util.Collection;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author wilco
 */
@Stateless
public class CourseFacade extends AbstractFacade<Course> {
    @PersistenceContext(unitName = "Digital_Learning_EnvironmentPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CourseFacade() {
        super(Course.class);
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
    public void addCompetency(Course course, Competency competency) {
        Collection<Competency> competencies = course.getCompetencyCollection();
        competencies.add(competency);
        course.setCompetencyCollection(competencies);
        store(course);
    }
    
    public void removeCompetency(Course course, Competency competency) {
        Collection<Competency> competencies = course.getCompetencyCollection();
        competencies.remove(competency);
        course.setCompetencyCollection(competencies);
        store(course);
    }
}
