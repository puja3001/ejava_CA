/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca1.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vrinda
 */
@Entity
@Table(name = "appointment", catalog = "appointments", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findByApptId", query = "SELECT a FROM Appointment a WHERE a.apptId = :apptId"),
    @NamedQuery(name = "Appointment.findByApptDate", query = "SELECT a FROM Appointment a WHERE a.apptDate = :apptDate"),
    @NamedQuery(name = "Appointment.findByEmail", query = "SELECT a FROM Appointment a WHERE a.people.email = :email")
})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "appt_id", nullable = false)
    private Integer apptId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "description", nullable = false, length = 16777215)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "appt_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date apptDate;
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)
    @ManyToOne(optional = false)
    private People people;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Appointment() {
    }

    public Appointment(Integer apptId) {
        this.apptId = apptId;
    }

    public Appointment(Integer apptId, String description, Date apptDate) {
        this.apptId = apptId;
        this.description = description;
        this.apptDate = apptDate;
    }

    public Integer getApptId() {
        return apptId;
    }

    public void setApptId(Integer apptId) {
        this.apptId = apptId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getApptDate() {
        return apptDate;
    }

    public void setApptDate(Date apptDate) {
        this.apptDate = apptDate;
    }

    
    public JsonObject toJSON(){
        return(Json.createObjectBuilder()
                .add("appointmentId",apptId)
                .add("dateTime",apptDate.getTime())
                .add("description",description)
                .add("personId",people.getPid()).build());
    }
   
    
}
