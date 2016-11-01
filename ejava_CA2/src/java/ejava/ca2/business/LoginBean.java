package ejava.ca2.business;


import ejava.ca2.model.Users;
import ejava.ca2.model.Groups;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author agarwal.puja
 */
@Stateless
public class LoginBean {
    
    @PersistenceContext private EntityManager em;
    
    public void register(Users user, Groups group){
        
        em.persist(user);
        em.persist(group);
        
    }
}
