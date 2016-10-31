/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.business;

import ejava.ca2.model.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Vrinda
 */
@Stateless
public class LoginBean {
     @PersistenceContext private EntityManager em;
    
    public void create(Users user){
        
        em.persist(user);
    }
}
