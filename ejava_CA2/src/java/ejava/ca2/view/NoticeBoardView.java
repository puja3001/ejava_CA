/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.view;

import ejava.ca2.business.NoteBean;
import ejava.ca2.model.Notes;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author agarwal.puja
 */
@RequestScoped
@Named
public class NoticeBoardView implements Serializable {
	private static final long serialVersionUID = 1L;
        
        @EJB private NoteBean notesBean;
        
        private String category;
        private List<Notes> categoryNotes = new LinkedList();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Notes> getCategoryNotes() {
        return categoryNotes;
    }

    public void setCategoryNotes(List<Notes> categoryNotes) {
        this.categoryNotes = categoryNotes;
    }
    
    public void loadAllPostsFromCategory(){
             
        categoryNotes = notesBean.findNotesByCategory(category);
    }
}
