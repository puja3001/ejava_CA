/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.rest;

import ejava.ca2.business.NoteBean;
import ejava.ca2.model.Notes;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author agarwal.puja
 */
@RequestScoped
@Path("/noticeboard")
public class NoticeBoardResource {
    
    @EJB private NoteBean noteBean;
    
    @Resource(mappedName = "concurrent/myThreadpool")
    private ManagedExecutorService executorService;
    
    @GET
    //@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public void get(
            
            @Suspended final AsyncResponse asyncResponse,
            @QueryParam(value = "category") final String category){
        executorService.submit(() -> {
            asyncResponse.resume(doGet(category));
        });
     
     }

    private Response doGet(String category) {
        List<Notes> noteslist = new LinkedList();
        if(category.equals("All")){
            noteslist = noteBean.findAllNotes();
        }
        else{
               noteslist = noteBean.findNotesByCategory(category); 
        }
        JsonArrayBuilder notesBuilder = Json.createArrayBuilder();
        noteslist.stream().forEach((apt) -> {
            notesBuilder.add(apt.toJSON());
         });
        
        return(Response.ok(notesBuilder.build()).build());
    }
    
}
