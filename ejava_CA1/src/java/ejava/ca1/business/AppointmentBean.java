
package ejava.ca1.business;

import ejava.ca1.model.Appointment;
import ejava.ca1.model.People;
import java.util.List;
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
public class AppointmentBean {
    
    @PersistenceContext private EntityManager em;
    
    public List<Appointment> get(String email){
        TypedQuery<Appointment> query = em.createNamedQuery("Appointment.findByEmail",Appointment.class);
        query.setParameter("email", email);
        return(query.getResultList());
        
    }
    
    public void add(Appointment appointment){
        em.persist(appointment);
    }
    
}
