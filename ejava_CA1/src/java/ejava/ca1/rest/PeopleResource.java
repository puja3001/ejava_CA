/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca1.rest;

import ejava.ca1.business.PeopleBean;
import ejava.ca1.model.People;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
@Path("/people")
public class PeopleResource {
    
    @EJB private PeopleBean peopleBean;
    
    @Resource(mappedName = "concurrent/myThreadpool")
    private ExecutorService executorService;
     
    
    
    @GET
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public void get(
            @Suspended final AsyncResponse asyncResponse,
            @QueryParam(value = "email") final String email){
        executorService.submit(() -> {
            asyncResponse.resume(doGet(email));
        });
     
     }

    private Response doGet(String email) {

        Optional<People> p = peopleBean.get(email);
        if(!p.isPresent()) return (Response.status(Response.Status.NOT_FOUND).build());
        
        JsonObjectBuilder apptBuilder = Json.createObjectBuilder();
        apptBuilder.add("pid", p.get().getPid());
        apptBuilder.add("email", p.get().getEmail());
        apptBuilder.add("name", p.get().getName());

        return(Response.ok(apptBuilder.build()).build());
    }

    @POST
    @Consumes(value = "application/x-www-form-urlencoded")
    public void post(@Suspended final AsyncResponse asyncResponse, final MultivaluedMap<String,String> formData) {
        executorService.submit(() -> {
            asyncResponse.resume(doPost(formData));
        });
    }

    private Response doPost(MultivaluedMap<String,String> formData) {
        People people = new People();
        people.setName(formData.getFirst("name"));
        people.setEmail(formData.getFirst("email"));
        String id = UUID.randomUUID().toString().substring(0, 8);
        people.setPid(id);
        peopleBean.create(people);
        return(Response.ok().build());
    }
    
}
