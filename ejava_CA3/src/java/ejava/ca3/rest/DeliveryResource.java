/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.rest;

import ejava.ca3.business.DeliveryBean;
import ejava.ca3.business.PodBean;
import ejava.ca3.model.Delivery;
import ejava.ca3.model.Pod;
import java.math.BigDecimal;
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
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author agarwal.puja
 */

@RequestScoped
@Path("/items")
public class DeliveryResource {
    
    @EJB private DeliveryBean deliveryBean;
    
    @EJB private PodBean podBean;
    
    @Resource(mappedName = "concurrent/noticeThreadPool")
    private ManagedExecutorService executorService;
    
    @GET
    //@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public void get(
            
            @Suspended final AsyncResponse asyncResponse){
        executorService.submit(() -> {
            asyncResponse.resume(doGet());
        });
     
     }

    private Response doGet() {
        List<Pod> delList = new LinkedList();
        delList = podBean.findAll(); 
        JsonArrayBuilder deliveryBuilder = Json.createArrayBuilder();
        delList.stream().forEach((p) -> {
            deliveryBuilder.add(Json.createObjectBuilder()
                    .add("teamId", "1c794860")
                    .add("podId", p.getPodId())
                    .add("name",p.getPkgId().getName())
                    .add("address", p.getPkgId().getAddress())
                    .add("phone", p.getPkgId().getPhone())
            
            );
         });
        
        return(Response.ok(deliveryBuilder.build()).build());
    }
}
