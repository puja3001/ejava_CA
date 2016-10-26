/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca1.rest;

import ejava.ca1.business.AppointmentBean;
import ejava.ca1.business.PeopleBean;
import ejava.ca1.model.Appointment;
import ejava.ca1.model.People;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author Vrinda
 */
@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
    @EJB private AppointmentBean appointmentBean;
    @EJB private PeopleBean peopleBean;
    
    @Resource(mappedName = "concurrent/myThreadpool")
    private ManagedExecutorService executorService;
     
    @GET
    @Path("{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public void get(@Suspended
    final AsyncResponse asyncResponse, @PathParam(value = "email")
    final String email){
        executorService.submit(() -> {
            asyncResponse.resume(doGet(email));
        });
     
     }

    private Response doGet(String email) {

        List<Appointment> aplist = appointmentBean.get(email);
        JsonArrayBuilder apptBuilder = Json.createArrayBuilder();
        aplist.stream().forEach((apt) -> {
            apptBuilder.add(apt.toJSON());
         });
        
        return(Response.ok(apptBuilder.build()).build());
    }
    
    
    @POST
    @Consumes(value = "application/x-www-form-urlencoded")
    public void post(@Suspended final AsyncResponse asyncResponse, final MultivaluedMap<String,String> formData) {
        executorService.submit(() -> {
            asyncResponse.resume(doCreateAppointment(formData));
        });
    }

    private Response doCreateAppointment(MultivaluedMap<String,String> formData) {
        
        Optional<People> p = peopleBean.get(formData.getFirst("email"));
        if(!p.isPresent()) return Response.status(Response.Status.NOT_FOUND).build();
      
        Appointment a = new Appointment();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.valueOf(formData.getFirst("date")));
        a.setDescription(formData.getFirst("description"));
        a.setApptDate(c.getTime());
        a.setPeople(p.get());
        try{
            appointmentBean.add(a);
            JsonObject json = Json.createObjectBuilder()
                  .add("email", formData.getFirst("email"))
                  .add("appt_date", formData.getFirst("date"))
                  .add("description", formData.getFirst("description"))
                  .build();

            return (Response.status(Response.Status.CREATED)
                  .entity(json.toString())
                  .build());
            
        }catch(Exception ex){
            return (Response.status(Response.Status.NOT_FOUND)
                .build());
        }
    }
}
