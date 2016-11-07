/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.business;

import ejava.ca3.model.Pod;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author agarwal.puja
 */
@Stateless
public class PodBean {
    
    
    @PersistenceContext private EntityManager em;
    
    public void create(Pod pod){
        em.persist(pod);
    }
    
}
