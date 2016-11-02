/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author agarwal.puja
 */
@Embeddable
public class NotesPK implements Serializable{
    
    @Column(name = "userId")
    private String userId;
    @Basic(optional = false)

    @Column(name = "timeOfCreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfCreation;
    
    @Column(name = "title")
    private String title;
    
    public NotesPK(){
        
    }

    public NotesPK(String userId, Date timeOfCreation, String title) {
        this.userId = userId;
        this.timeOfCreation = timeOfCreation;
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(Date timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
