/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.view;

import ejava.ca2.business.NoteBean;
import ejava.ca2.model.Notes;
import ejava.ca2.model.NotesPK;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author agarwal.puja
 */
@RequestScoped
@Named
public class NoteView implements Serializable {
	private static final long serialVersionUID = 1L;
        
        @EJB private NoteBean notesBean;
        
        private String title;
        private String category;
        private String content;
        
        private List<Notes> userNotes = new LinkedList();
        
        String userId = FacesContext.getCurrentInstance().getExternalContext().getUs‌​erPrincipal().getName();

    public List<Notes> getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(List<Notes> userNotes) {
        this.userNotes = userNotes;
    }
        
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void postNote(){
        
        System.out.println("user name: "+userId);
        NotesPK notesPK = new NotesPK();
        notesPK.setTitle(title);
        notesPK.setTimeOfCreation(new Date(Calendar.getInstance().getTimeInMillis()));
        notesPK.setUserId(userId);
        Notes note = new Notes(notesPK,content,category);
        notesBean.create(note);
        title="";content="";category="";
        onload();
    }
    
    public void onload(){
             
        userNotes = notesBean.findNotesByUser(userId);
    }

}
