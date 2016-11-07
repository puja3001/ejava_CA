/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.business;

import ejava.ca3.model.Pod;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TimerService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

/**
 *
 * @author agarwal.puja
 */
@Stateless
public class HQBean implements Serializable {
    
    @Resource TimerService timerService;
    @EJB PodBean podBean;
    
   
    @Schedule(minute="*/2")
    public void postToHQ(){    
        
        List<Pod> pods = new LinkedList();
        pods = podBean.findAll();
        
        for(Pod pod:pods){
            if(pod.getAckId() == null){
                Client client = ClientBuilder.newBuilder()
            .register(MultiPartFeature.class).build();
        String HQUrl = "http://10.10.0.50:8080/epod/upload";
        String callback = "http://10.10.24.30:8080/ejava_CA3/callback";
        WebTarget target = client.target(HQUrl);

        FileDataBodyPart imgPart = new FileDataBodyPart("image", 
				new File(pod.getPodId().toString()),
				MediaType.APPLICATION_OCTET_STREAM_TYPE);
		imgPart.setContentDisposition(
				FormDataContentDisposition.name("image")
				.fileName(pod.getPodId().toString()).build());
        
        MultiPart formData = new FormDataMultiPart()
                .field("teamId","1c794860",MediaType.TEXT_PLAIN_TYPE)
                .field("podId",pod.getPodId().toString(),MediaType.TEXT_PLAIN_TYPE)
                .field("note",pod.getNote(),MediaType.TEXT_PLAIN_TYPE)
                .field("callback",callback)
                .bodyPart(imgPart);
        
        formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        
        
        
         Invocation.Builder inv = target.request();

		System.out.println(">>> part: " + formData);

		Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));

		System.out.println(">> call resp:" + callResp.getStatus());
            }
        }
        
         
        
    }
    
}
