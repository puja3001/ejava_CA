/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.business;

import ejava.ca3.model.Pod;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    public void update(Pod pod){
       em.merge(pod);
    }
    
    public Optional<Pod> get(int id){
        TypedQuery<Pod> query = em.createNamedQuery("Pod.findByPodId",Pod.class);
        query.setParameter("podId",id);
        return (Optional.ofNullable(query.getSingleResult()));
    }

    public List<Pod> findAll() {
        TypedQuery<Pod> query = em.createNamedQuery("Pod.findAll",Pod.class);
        return(query.getResultList());
    }
    
}
