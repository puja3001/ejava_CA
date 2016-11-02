/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.websocket;

import java.io.IOException;
import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Vrinda
 */
@RequestScoped
@ServerEndpoint("/notices")
public class NoticeSocket {
    
    @Resource(lookup = "concurrent/schedThreadPool")
    private ManagedScheduledExecutorService executor;

	@OnOpen
	public void open(Session session) {
		System.out.println(">>> new session: " + session.getId());
	}
        
        @OnMessage
	public void message(final Session session, final String msg) {
		System.out.println(">>> message: " + msg);
                String[] msgArray = msg.split(":");
		executor.submit(() -> {
                    System.out.println(">>> in thread");
                    final JsonObject message = Json.createObjectBuilder()
                            .add("title", msgArray[0])
                            .add("timestamp", (new Date()).toString())
                            .add("category", msgArray[1])
                            .add("content", msgArray[2])
                            .build();
                    
                    session.getOpenSessions().stream().forEach((s) -> {
                        try {
                            s.getBasicRemote().sendText(message.toString());
                        } catch(IOException ex) {
                            try { s.close(); } catch (IOException e) { }
                        }
                    });
                });
		System.out.println(">>> exiting message");

        }
}
