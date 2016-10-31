package ejava.ca2.business;


import ejava.ca2.model.Users;
import ejava.ca2.model.Groups;
import ejava.ca2.model.GroupsPK;
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
    
    public void create(Users user){
        
        em.persist(user);
        GroupsPK groupPK = new GroupsPK();
        groupPK.setGroupid("valid");
        groupPK.setUserid(user.getUserid());
        Groups group = new Groups();
        group.setGroupsPK(groupPK);
        em.persist(group);
        
    }
}
    

