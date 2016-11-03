/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author agarwal.puja
 */
@Entity
@Table(name = "notes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notes.findAll", query = "SELECT n FROM Notes n"),
    @NamedQuery(name = "Notes.findByUserid", query = "SELECT n FROM Notes n WHERE n.notesPK.userId = :userId ORDER BY n.notesPK.timeOfCreation DESC"),
    @NamedQuery(name = "Notes.findByCategory", query = "SELECT n FROM Notes n WHERE n.category = :category ORDER BY n.notesPK.timeOfCreation DESC")})
public class Notes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private NotesPK notesPK;
    
    @Column(name = "content")
    private String content;

    public Notes(){
        
    }
    
    public Notes(NotesPK notesPK, String content, String category) {
        this.notesPK = notesPK;
        this.content = content;
        this.category = category;
    }
    
    @Column(name = "category")
    private String category;

    public NotesPK getNotesPK() {
        return notesPK;
    }

    public void setNotesPK(NotesPK notesPK) {
        this.notesPK = notesPK;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
