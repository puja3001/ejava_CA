/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.rest;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Path;

/**
 *
 * @author agarwal.puja
 */

@RequestScoped
@Path("/item")
public class DeliveryResource {
    
    @Resource(mappedName = "concurrent/myThreadpool")
    private ManagedExecutorService executorService;
    
    
}
