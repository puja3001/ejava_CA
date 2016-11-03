/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.business;

import ejava.ca2.model.Notes;
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
public class NoteBean {
    
    @PersistenceContext private EntityManager em;
    
    public void create(Notes note){
        em.persist(note);
    }
    
    public List<Notes> findNotesByUser(String userId){
        TypedQuery<Notes> query = em.createNamedQuery("Notes.findByUserid",Notes.class);
        query.setParameter("userId", userId);
        return(query.getResultList());
    }
    
    public List<Notes> findNotesByCategory(String category){
        TypedQuery<Notes> query = em.createNamedQuery("Notes.findByCategory",Notes.class);
        query.setParameter("category", category);
        return(query.getResultList());
    }
        
}
