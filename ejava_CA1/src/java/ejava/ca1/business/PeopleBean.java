
package ejava.ca1.business;

import ejava.ca1.model.Appointment;
import ejava.ca1.model.People;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Vrinda
 */
@Stateless
public class PeopleBean {
    
    @PersistenceContext private EntityManager em;
    
    public void create(People people){
        
        em.persist(people);
    }
    
    public Optional<People> get(String email){
        TypedQuery<People> query = em.createNamedQuery("People.findByEmail", People.class);
        query.setParameter("email", email);
         return (Optional.ofNullable(query.getSingleResult()));
        
    }
}
