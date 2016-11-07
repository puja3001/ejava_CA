/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.business;

import ejava.ca3.model.Delivery;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author agarwal.puja
 */
@Stateless
public class DeliveryBean {
    
    @PersistenceContext private EntityManager em;
    
    public Delivery create(Delivery delivery){
        em.persist(delivery);
        return em.merge(delivery);
    }
    
    public Delivery getDelivery(Delivery delivery){
        return em.merge(delivery);
    }
    
    public List<Delivery> findAll(){
        TypedQuery<Delivery> query = em.createNamedQuery("Delivery.findAll",Delivery.class);
        return(query.getResultList());
    }
    
}
